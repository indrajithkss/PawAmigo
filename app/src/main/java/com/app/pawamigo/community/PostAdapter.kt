package com.app.pawamigo.community

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pawamigo.R
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PostAdapter(
    private val ctx: Context,
    private val items: List<Post>,
    private val onLikeClicked: (Post) -> Unit,
    private val onCommentClicked: (Post) -> Unit,
    private val onMoreClicked: (Post, View) -> Unit
) : RecyclerView.Adapter<PostAdapter.VH>() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        val imgAvatar: ImageView = v.findViewById(R.id.imgAvatar)
        val tvUserName: TextView = v.findViewById(R.id.tvUserName)
        val btnMore: ImageButton = v.findViewById(R.id.btnMore)

        val imgPost: ImageView = v.findViewById(R.id.imgPost)
        val imgDoubleTap: ImageView = v.findViewById(R.id.imgDoubleTapHeart)

        val btnLike: ImageButton = v.findViewById(R.id.btnLike)
        val btnComment: ImageButton = v.findViewById(R.id.btnComment)
        val btnShare: ImageButton = v.findViewById(R.id.btnShare)

        val tvLikeCount: TextView = v.findViewById(R.id.tvLikeCount)
        val tvCaption: TextView = v.findViewById(R.id.tvCaption)
        val tvTime: TextView = v.findViewById(R.id.tvTime)

        val imageFrame: View = v.findViewById(R.id.imageFrame)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_post_instagram, parent, false)
        return VH(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val post = items[position]

        // ----------------------------
        // BASIC POST DATA
        // ----------------------------
        holder.tvUserName.text = post.userName
        holder.tvCaption.text = "${post.userName} ${post.text.orEmpty()}"
        holder.tvTime.text = timeAgo(post.timestamp)
        holder.tvLikeCount.text = "${post.likeCount} likes"

        // Avatar
        Glide.with(ctx)
            .load(post.userAvatar)
            .circleCrop()
            .placeholder(R.drawable.ic_paw)
            .into(holder.imgAvatar)

        // ----------------------------
        // LOAD LOCAL URI POST IMAGE
        // ----------------------------
        if (!post.imageUrl.isNullOrEmpty()) {
            val uri = Uri.parse(post.imageUrl)
            holder.imgPost.visibility = View.VISIBLE

            Glide.with(ctx)
                .load(uri)
                .into(holder.imgPost)

        } else {
            holder.imgPost.visibility = View.GONE
        }

        // ----------------------------
        // LIKE BUTTON STATE
        // ----------------------------
        val uid = auth.currentUser?.uid
        if (uid != null) {
            db.collection("community_posts")
                .document(post.postId)
                .collection("likes")
                .document(uid)
                .get()
                .addOnSuccessListener { doc ->
                    holder.btnLike.setImageResource(
                        if (doc.exists()) R.drawable.ic_heart_filled
                        else R.drawable.ic_heart_outline
                    )
                }
        }

        // ----------------------------
        // LIKE CLICK
        // ----------------------------
        holder.btnLike.setOnClickListener {
            onLikeClicked(post)
            animateLike(holder)
        }

        // ----------------------------
        // COMMENT BUTTON
        // ----------------------------
        holder.btnComment.setOnClickListener {
            onCommentClicked(post)
        }

        // ----------------------------
        // MORE BUTTON
        // ----------------------------
        holder.btnMore.setOnClickListener {
            onMoreClicked(post, holder.btnMore)
        }

        // ----------------------------
        // SHARE BUTTON
        // ----------------------------
        holder.btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "${post.userName}: ${post.text}")
            ctx.startActivity(Intent.createChooser(intent, "Share Post"))
        }

        // -------------------------------------------------------------
        // ⭐ MANUAL DOUBLE-TAP DETECTION (BUG-PROOF, NO OVERRIDE NEEDED)
        // -------------------------------------------------------------
        var lastTap = 0L

        holder.imageFrame.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val now = System.currentTimeMillis()
                if (now - lastTap < 250) {
                    // DOUBLE TAP DETECTED
                    onLikeClicked(post)
                    showDoubleTapHeart(holder)
                }
                lastTap = now
            }
            true
        }
    }

    // ----------------------------
    // LIKE ANIMATION
    // ----------------------------
    private fun animateLike(holder: VH) {
        val anim = ScaleAnimation(
            0.7f, 1f, 0.7f, 1f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        )
        anim.duration = 200
        holder.btnLike.startAnimation(anim)
    }

    // ----------------------------
    // DOUBLE TAP HEART ANIMATION
    // ----------------------------
    private fun showDoubleTapHeart(holder: VH) {
        holder.imgDoubleTap.visibility = View.VISIBLE
        holder.imgDoubleTap.alpha = 1f
        holder.imgDoubleTap.scaleX = 0.5f
        holder.imgDoubleTap.scaleY = 0.5f

        holder.imgDoubleTap.animate()
            .scaleX(1f)
            .scaleY(1f)
            .alpha(0f)
            .setDuration(600)
            .withEndAction {
                holder.imgDoubleTap.visibility = View.GONE
            }
            .start()
    }
}

package com.app.pawamigo.community

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pawamigo.R
import com.bumptech.glide.Glide

class CommentsAdapter(
    private val ctx: Context,
    private val items: List<Comment>
) : RecyclerView.Adapter<CommentsAdapter.VH>() {

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        val imgAvatar: ImageView = v.findViewById(R.id.imgCommentAvatar)
        val tvUser: TextView = v.findViewById(R.id.tvCommentUser)
        val tvText: TextView = v.findViewById(R.id.tvCommentText)
        val tvTime: TextView = v.findViewById(R.id.tvCommentTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_comment, parent, false)
        return VH(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val c = items[position]

        holder.tvUser.text = c.userName
        holder.tvText.text = c.text
        holder.tvTime.text = timeAgo(c.timestamp)

        // Safe avatar loading
        if (!c.userAvatar.isNullOrEmpty()) {
            Glide.with(ctx)
                .load(c.userAvatar)
                .circleCrop()
                .placeholder(R.drawable.ic_paw)  // fallback icon
                .into(holder.imgAvatar)
        } else {
            holder.imgAvatar.setImageResource(R.drawable.ic_paw)
        }
    }
}

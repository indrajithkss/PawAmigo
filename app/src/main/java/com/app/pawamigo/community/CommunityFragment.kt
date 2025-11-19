package com.app.pawamigo.community

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.pawamigo.databinding.FragmentCommunityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class CommunityFragment : Fragment() {

    private var _binding: FragmentCommunityBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val posts = mutableListOf<Post>()
    private lateinit var adapter: PostAdapter
    private var sortMode = SortMode.NEWEST

    enum class SortMode { NEWEST, TOP }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = PostAdapter(requireContext(), posts,
            onLikeClicked = { toggleLike(it) },
            onCommentClicked = { openComments(it) },
            onMoreClicked = { post, anchor -> handleMoreOptions(post) }
        )
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPosts.adapter = adapter

        binding.btnCreatePost.setOnClickListener {
            startActivityForResult(Intent(requireContext(), CreatePostActivity::class.java), REQUEST_CREATE)
        }

        binding.btnSortNewest.setOnClickListener {
            sortMode = SortMode.NEWEST
            loadPosts()
        }
        binding.btnSortTop.setOnClickListener {
            sortMode = SortMode.TOP
            loadPosts()
        }

        loadPosts()
    }

    private fun loadPosts() {
        val col = db.collection("community_posts")
        val query: Query = when (sortMode) {
            SortMode.NEWEST -> col.orderBy("timestamp", Query.Direction.DESCENDING)
            SortMode.TOP -> col.orderBy("likeCount", Query.Direction.DESCENDING)
        }

        query.addSnapshotListener { snap, err ->
            if (err != null) return@addSnapshotListener
            posts.clear()
            snap?.documents?.forEach { doc ->
                val p = doc.toObject(Post::class.java)
                p?.postId = doc.id
                if (p != null) posts.add(p)
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun toggleLike(post: Post) {
        val uid = auth.currentUser?.uid ?: return
        val postRef = db.collection("community_posts").document(post.postId)
        val likeRef = postRef.collection("likes").document(uid)

        db.runTransaction { tx ->
            val liked = tx.get(likeRef).exists()
            val currentLikes = tx.get(postRef).getLong("likeCount") ?: 0L
            if (liked) {
                tx.delete(likeRef)
                tx.update(postRef, "likeCount", currentLikes - 1)
            } else {
                tx.set(likeRef, mapOf("timestamp" to System.currentTimeMillis()))
                tx.update(postRef, "likeCount", currentLikes + 1)
            }
        }
    }

    private fun openComments(post: Post) {
        startActivity(Intent(requireContext(), CommentsActivity::class.java).apply {
            putExtra("postId", post.postId)
        })
    }

    private fun handleMoreOptions(post: Post) {
        val uid = auth.currentUser?.uid ?: return
        if (uid == post.userId) {
            // delete post and optionally delete storage image
            db.collection("community_posts").document(post.postId).delete()
        } else {
            // future: report
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CREATE && resultCode == Activity.RESULT_OK) {
            // feed will update via listener
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val REQUEST_CREATE = 1100
    }
}

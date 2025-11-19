package com.app.pawamigo.community

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.pawamigo.databinding.ActivityCommentsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

data class Comment(
    var commentId: String = "",
    var userId: String = "",
    var userName: String = "",
    var userAvatar: String? = null,
    var text: String = "",
    var timestamp: Long = 0L
)

class CommentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentsBinding
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val comments = mutableListOf<Comment>()
    private lateinit var adapter: CommentsAdapter
    private var postId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postId = intent.getStringExtra("postId") ?: return
        adapter = CommentsAdapter(this, comments)
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        binding.rvComments.adapter = adapter

        binding.btnSend.setOnClickListener {
            val text = binding.etComment.text.toString().trim()
            if (text.isNotEmpty()) addComment(text)
        }

        listenComments()
    }

    private fun listenComments() {
        db.collection("community_posts").document(postId!!)
            .collection("comments")
            .orderBy("timestamp")
            .addSnapshotListener { snap, err ->
                if (err != null) return@addSnapshotListener
                comments.clear()
                snap?.documents?.forEach { doc ->
                    val c = doc.toObject(Comment::class.java)
                    c?.commentId = doc.id
                    if (c != null) comments.add(c)
                }
                adapter.notifyDataSetChanged()
            }
    }

    private fun addComment(text: String) {
        val uid = auth.currentUser?.uid ?: return
        val commentRef = db.collection("community_posts").document(postId!!)
            .collection("comments").document()
        val c = Comment().apply {
            commentId = commentRef.id
            userId = uid
            userName = auth.currentUser?.displayName ?: "Unknown"
            userAvatar = auth.currentUser?.photoUrl?.toString()
            this.text = text
            timestamp = System.currentTimeMillis()
        }
        commentRef.set(c).addOnSuccessListener { binding.etComment.setText("") }
    }
}

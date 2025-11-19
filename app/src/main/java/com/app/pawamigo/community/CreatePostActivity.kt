package com.app.pawamigo.community

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.pawamigo.databinding.ActivityCreatePostBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CreatePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding
    private val PICK_IMAGE = 1200
    private var imageUri: Uri? = null

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pick image locally
        binding.btnPickImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply { type = "image/*" }
            startActivityForResult(intent, PICK_IMAGE)
        }

        binding.btnPost.setOnClickListener {
            createLocalPost()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            binding.imgPreview.setImageURI(imageUri)
            binding.imgPreview.visibility = android.view.View.VISIBLE
        }
    }

    private fun createLocalPost() {
        val uid = auth.currentUser?.uid ?: return
        val text = binding.etPostText.text.toString().trim()

        val doc = db.collection("community_posts").document()

        val post = Post(
            postId = doc.id,
            userId = uid,
            userName = auth.currentUser?.displayName ?: "User",
            userAvatar = null,
            text = text,
            imageUrl = imageUri?.toString(),   // ⭐ LOCAL URI ONLY
            timestamp = System.currentTimeMillis(),
            likeCount = 0
        )

        doc.set(post)
            .addOnSuccessListener {
                setResult(Activity.RESULT_OK)
                finish()
            }
    }
}

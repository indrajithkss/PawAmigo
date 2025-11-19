package com.app.pawamigo.ui.onboarding

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.app.pawamigo.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import de.hdodenhof.circleimageview.CircleImageView

class OwnerRegistrationActivity : AppCompatActivity() {

    private lateinit var etFirstName: TextInputEditText
    private lateinit var etLastName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var etLocation: TextInputEditText
    private lateinit var spinnerState: Spinner
    private lateinit var rgExperience: RadioGroup
    private lateinit var btnContinueToPetInfo: MaterialButton
    private lateinit var btnUploadProfile: Button
    private lateinit var ownerProfileImage: CircleImageView

    private var selectedImageUri: Uri? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    // Image picker launcher
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                selectedImageUri = it
                ownerProfileImage.setImageURI(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner_registration)

        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Initialize Views
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etPhone = findViewById(R.id.etPhone)
        etLocation = findViewById(R.id.etLocation)
        spinnerState = findViewById(R.id.spinnerState)
        rgExperience = findViewById(R.id.rgExperience)
        btnContinueToPetInfo = findViewById(R.id.btnContinueToPetInfo)
        btnUploadProfile = findViewById(R.id.btnUploadProfile)
        ownerProfileImage = findViewById(R.id.ownerProfileImage)

        // State dropdown
        val states = arrayOf(
            "Select State", "Kerala", "Tamil Nadu", "Karnataka", "Maharashtra",
            "Delhi", "Goa", "Gujarat", "Rajasthan", "Punjab", "Other"
        )
        spinnerState.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, states)

        // Upload image
        btnUploadProfile.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        // Continue button action
        btnContinueToPetInfo.setOnClickListener {
            registerOwner()
        }
    }

    private fun registerOwner() {
        val firstName = etFirstName.text.toString().trim()
        val lastName = etLastName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val location = etLocation.text.toString().trim()
        val state = spinnerState.selectedItem.toString()
        val experienceId = rgExperience.checkedRadioButtonId
        val experience =
            if (experienceId != -1) findViewById<RadioButton>(experienceId).text.toString() else ""

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() ||
            phone.isEmpty() || location.isEmpty() || state == "Select State" || experience.isEmpty()
        ) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Create user in Firebase Auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val userId = it.user?.uid ?: return@addOnSuccessListener

                val owner = hashMapOf(
                    "userId" to userId,
                    "firstName" to firstName,
                    "lastName" to lastName,
                    "email" to email,
                    "phone" to phone,
                    "location" to location,
                    "state" to state,
                    "experience" to experience,
                    "profileImageUri" to (selectedImageUri?.toString() ?: "")
                )

                firestore.collection("owners")
                    .document(userId)
                    .set(owner)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Owner Registration Successful", Toast.LENGTH_SHORT)
                            .show()

                        val intent = Intent(this, PetRegistrationActivity::class.java)
                        intent.putExtra("ownerId", userId)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this,
                            "Firestore Error: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}

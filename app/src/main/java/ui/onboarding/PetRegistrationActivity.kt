package com.app.pawamigo.ui.onboarding

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.app.pawamigo.databinding.ActivityPetRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PetRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetRegistrationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private var selectedPetImageUri: Uri? = null

    // Image picker launcher
    private val pickPetImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                selectedPetImageUri = it
                binding.petProfileImage.setImageURI(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // 🐾 Pet Photo Upload
        binding.btnUploadPetPhoto.setOnClickListener {
            pickPetImageLauncher.launch("image/*")
        }

        // 🐾 Back Button (ensure it exists in your XML)
        binding.btnBack?.setOnClickListener {
            val intent = Intent(this, OwnerRegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 🐾 Continue Button
        binding.btnContinue.setOnClickListener {
            savePetDetails()
        }
    }

    private fun savePetDetails() {
        val petType =
            when {
                binding.radioDog.isChecked -> "Dog"
                binding.radioCat.isChecked -> "Cat"
                else -> ""
            }

        val petName = binding.etPetName.text.toString().trim()
        val petAge = binding.etPetAge.text.toString().trim()
        val petBreed = binding.etPetBreed.text.toString().trim()
        val petColor = binding.etPetColor.text.toString().trim()
        val petSize = binding.spinnerSize.selectedItem.toString()
        val gender =
            when {
                binding.radioMale.isChecked -> "Male"
                binding.radioFemale.isChecked -> "Female"
                else -> ""
            }
        val vaccinated =
            when {
                binding.radioVaccinated.isChecked -> "Vaccinated"
                binding.radioNotVaccinated.isChecked -> "Not Vaccinated"
                else -> ""
            }
        val friendly = binding.checkboxFriendly.isChecked
        val aggressive = binding.checkboxAggressive.isChecked
        val playful = binding.checkboxPlayful.isChecked

        if (petType.isEmpty() || petName.isEmpty() || petAge.isEmpty() || petBreed.isEmpty() ||
            petColor.isEmpty() || gender.isEmpty() || vaccinated.isEmpty()
        ) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val userId = auth.currentUser?.uid ?: return
        val petData = hashMapOf(
            "petType" to petType,
            "petName" to petName,
            "petAge" to petAge,
            "petBreed" to petBreed,
            "petColor" to petColor,
            "petSize" to petSize,
            "gender" to gender,
            "vaccinated" to vaccinated,
            "friendly" to friendly,
            "aggressive" to aggressive,
            "playful" to playful,
            "petImageUri" to (selectedPetImageUri?.toString() ?: "")
        )

        db.collection("owners").document(userId)
            .collection("petDetails").document()
            .set(petData)
            .addOnSuccessListener {
                Toast.makeText(this, "Pet registered successfully!", Toast.LENGTH_SHORT).show()

                // Navigate to Preferences Page
                val intent = Intent(this, PreferencesActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}

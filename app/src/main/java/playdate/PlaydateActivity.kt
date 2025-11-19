package com.app.pawamigo.ui.playdate

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.pawamigo.databinding.ActivityPlaydateBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class PlaydateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaydateBinding
    private val db = FirebaseFirestore.getInstance()
    private var currentStep = 1   // Tracks which step user is on

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaydateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupStepNavigation()
        setupPickers()
    }

    // --------------------------------------------------------
    // STEP PICKERS (DATE + TIME)
    // --------------------------------------------------------
    private fun setupPickers() {

        binding.tvDate.setOnClickListener {
            val c = Calendar.getInstance()
            val dp = DatePickerDialog(
                this,
                { _, year, month, day ->
                    binding.tvDate.text = "$day/${month + 1}/$year"
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )
            dp.show()
        }

        binding.tvTime.setOnClickListener {
            val c = Calendar.getInstance()
            val tp = TimePickerDialog(
                this,
                { _, hour, minute ->
                    binding.tvTime.text = String.format("%02d:%02d", hour, minute)
                },
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                false
            )
            tp.show()
        }
    }

    // --------------------------------------------------------
    // STEP NAVIGATION (NEXT + BACK)
    // --------------------------------------------------------
    private fun setupStepNavigation() {

        binding.btnNext.setOnClickListener {
            if (currentStep < 4) {
                currentStep++
                updateStepUI()
            } else {
                saveToFirebase()
            }
        }

        binding.btnBack.setOnClickListener {
            if (currentStep > 1) {
                currentStep--
                updateStepUI()
            } else {
                finish()
            }
        }
    }

    // --------------------------------------------------------
    // SHOW ONLY CURRENT STEP
    // --------------------------------------------------------
    private fun updateStepUI() {

        binding.step1.visibility = if (currentStep == 1) android.view.View.VISIBLE else android.view.View.GONE
        binding.step2.visibility = if (currentStep == 2) android.view.View.VISIBLE else android.view.View.GONE
        binding.step3.visibility = if (currentStep == 3) android.view.View.VISIBLE else android.view.View.GONE
        binding.step4.visibility = if (currentStep == 4) android.view.View.VISIBLE else android.view.View.GONE

        binding.btnNext.text = if (currentStep == 4) "Finish" else "Next"
    }

    // --------------------------------------------------------
    // SAVE SCHEDULED PLAYDATE TO FIREBASE
    // --------------------------------------------------------
    private fun saveToFirebase() {

        val date = binding.tvDate.text.toString()
        val time = binding.tvTime.text.toString()
        val location = binding.etLocation.text.toString()
        val notes = binding.etNotes.text.toString()

        if (date == "Select Date" || time == "Select Time") {
            Toast.makeText(this, "Please select date & time", Toast.LENGTH_SHORT).show()
            return
        }

        val playdate = hashMapOf(
            "date" to date,
            "time" to time,
            "location" to location,
            "notes" to notes,
            "status" to "Pending",
            "createdAt" to System.currentTimeMillis()
        )

        db.collection("Playdates")
            .add(playdate)
            .addOnSuccessListener {
                Toast.makeText(this, "Playdate Scheduled Successfully!", Toast.LENGTH_LONG).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to save playdate", Toast.LENGTH_LONG).show()
            }
    }
}

package com.app.pawamigo.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.pawamigo.R

class PreferenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        val btnContinue: Button = findViewById(R.id.btnContinue)
        val btnBack: Button = findViewById(R.id.btnBack)

        btnContinue.setOnClickListener {
            val intent = Intent(this, PreferencesActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnBack.setOnClickListener {
            val intent = Intent(this, PetRegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

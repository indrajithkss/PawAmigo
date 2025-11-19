package com.app.pawamigo.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.pawamigo.R
import com.app.pawamigo.ui.dashboard.DashboardActivity

class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val btnContinue: Button = findViewById(R.id.btnContinue)
        val btnBack: Button = findViewById(R.id.btnBack)

        btnContinue.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnBack.setOnClickListener {
            val intent = Intent(this, PreferenceActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

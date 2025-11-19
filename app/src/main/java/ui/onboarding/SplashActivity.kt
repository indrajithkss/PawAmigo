package com.app.pawamigo.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.app.pawamigo.R
import com.app.pawamigo.ui.onboarding.LoginActivity
import com.app.pawamigo.ui.dashboard.DashboardActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    // ✅ Initialize FirebaseAuth variable
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // ✅ Initialize Firebase Auth instance
        auth = FirebaseAuth.getInstance()

        // Get current user
        val user = auth.currentUser

        // ✅ Splash delay (2 seconds)
        Handler(Looper.getMainLooper()).postDelayed({
            if (user != null) {
                // User already logged in → go to Dashboard
                val intent = Intent(this, DashboardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                // User not logged in → go to Login page
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            finish()
        }, 2000)
    }
}

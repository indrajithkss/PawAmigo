package com.app.pawamigo.ui.dashboard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.pawamigo.R
import com.app.pawamigo.databinding.ActivityDashboardBinding
import com.app.pawamigo.community.CommunityFragment

// Import fragments
import com.app.pawamigo.fragments.HomeFragment
import com.app.pawamigo.fragments.DiscoverFragment
import com.app.pawamigo.fragments.ActivityFragment
import android.content.Intent
import com.app.pawamigo.ui.playdate.PlaydateActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private var activeFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🏠 Load correct default fragment (HOME)
        if (savedInstanceState == null) {
            val homeFragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.dashboard_fragment_container, homeFragment)
                .commit()

            activeFragment = homeFragment   // FIXED
        }

        // 🧭 Bottom navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_discover -> DiscoverFragment()
                R.id.nav_community -> CommunityFragment()
                R.id.nav_activity -> ActivityFragment()
                else -> HomeFragment()
            }

            if (selectedFragment::class != activeFragment!!::class) {
                loadFragment(selectedFragment)
            }

            true
        }

        // ➕ FAB Action
        binding.fabAddPlaydate.setOnClickListener {
            startActivity(Intent(this, PlaydateActivity::class.java))
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out
            )
            .replace(R.id.dashboard_fragment_container, fragment)
            .commit()

        activeFragment = fragment
    }
}

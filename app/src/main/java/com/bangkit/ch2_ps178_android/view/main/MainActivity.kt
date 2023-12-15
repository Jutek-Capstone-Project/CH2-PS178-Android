package com.bangkit.ch2_ps178_android.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.databinding.ActivityMainBinding
import com.bangkit.ch2_ps178_android.view.history.HistoryFragment
import com.bangkit.ch2_ps178_android.view.home.HomeFragment
import com.bangkit.ch2_ps178_android.view.profile.ProfileFragment
import com.bangkit.ch2_ps178_android.view.welcome.WelcomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), ProfileFragment.LogoutListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNav: BottomNavigationView
    val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        bottomNav = binding.bottomNav

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            setupFragments()
            setupBottomNavigation()
        } else {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }
    }

    private fun setupFragments() {
        loadFragment(HomeFragment())
    }

    private fun setupBottomNavigation() {
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.history -> {
                    loadFragment(HistoryFragment())
                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    override fun onLogout() {
        firebaseAuth.signOut()
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }
}
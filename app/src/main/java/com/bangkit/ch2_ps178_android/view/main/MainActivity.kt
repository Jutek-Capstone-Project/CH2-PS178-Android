package com.bangkit.ch2_ps178_android.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.model.Base_model
import com.bangkit.ch2_ps178_android.databinding.ActivityMainBinding
import com.bangkit.ch2_ps178_android.view.ViewModelFactory
import com.bangkit.ch2_ps178_android.view.history.HistoryFragment
import com.bangkit.ch2_ps178_android.view.home.HomeFragment
import com.bangkit.ch2_ps178_android.view.profile.ProfileFragment
import com.bangkit.ch2_ps178_android.view.welcome.WelcomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.bangkit.ch2_ps178_android.data.adapter.MainAdapter
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapter_row

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        }





        //Load fragment secara default
        loadFragment(HomeFragment())

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
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

                else -> {
                    false
                }
            }
        }
    }

    fun logout() {
        viewModel.logout()
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

}
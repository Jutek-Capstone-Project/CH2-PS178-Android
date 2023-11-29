package com.agilizzulhaq.ch2_ps178_android.view.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.agilizzulhaq.ch2_ps178_android.R
import com.agilizzulhaq.ch2_ps178_android.data.model.UserModel
import com.agilizzulhaq.ch2_ps178_android.databinding.ActivityLoginBinding
import com.agilizzulhaq.ch2_ps178_android.view.ViewModelFactory
import com.agilizzulhaq.ch2_ps178_android.view.main.MainActivity
import com.agilizzulhaq.ch2_ps178_android.view.signup.SignupActivity
import com.agilizzulhaq.ch2_ps178_android.view.welcome.WelcomeActivity

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        val toolbar: Toolbar = findViewById(R.id.tb_login_back)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.editLoginEmail.text.toString()
            viewModel.saveSession(UserModel(email, "sample_token"))
            AlertDialog.Builder(this).apply {
                setTitle("Spontan? Uhuy!")
                setMessage("Anda berhasil login. Olahraga apa hari ini?")
                setPositiveButton("Lanjut") { _, _ ->
                    val intent = Intent(context, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                create()
                show()
            }
        }
        binding.tvLoginToSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
package com.bangkit.ch2_ps178_android.view.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.ch2_ps178_android.R
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bangkit.ch2_ps178_android.data.model.BaseModel
import com.bangkit.ch2_ps178_android.databinding.ActivityDetailBinding
import com.bangkit.ch2_ps178_android.databinding.ActivitySignupBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabBack.setOnClickListener {
            onBackPressed()
        }
    }
}
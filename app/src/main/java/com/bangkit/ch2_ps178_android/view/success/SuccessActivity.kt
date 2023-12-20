package com.bangkit.ch2_ps178_android.view.success

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.ch2_ps178_android.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
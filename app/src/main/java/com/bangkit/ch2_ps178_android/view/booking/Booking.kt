package com.bangkit.ch2_ps178_android.view.booking

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import com.bangkit.ch2_ps178_android.R

class Booking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        show_modal()
    }


    private fun show_modal() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.modal_infopem_layout)

        // Atur animasi agar dialog muncul dari bawah
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        // Set contentPadding menjadi nol untuk menghilangkan padding
        dialog.window?.decorView?.setPadding(0, 0, 0, 0)
        // Atur lebar dan tinggi dialog agar sesuai kebutuhan
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog.window?.setLayout(width, height)

        // Tampilkan dialog
        dialog.show()
    }
}
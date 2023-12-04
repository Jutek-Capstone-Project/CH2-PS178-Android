package com.bangkit.ch2_ps178_android.data.model

import android.content.Context
import android.widget.Toast

class Base_model {


    companion object {

        fun toast(context : Context, msg: String) {

            // Buat objek Toast dengan pesan dan durasi yang diinginkan
            val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)

            // Tampilkan toast
            toast.show()
        }

    }

}
package com.bangkit.ch2_ps178_android.data.model

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bangkit.ch2_ps178_android.R

class BaseModel {
    companion object {
        fun toast(context : Context, msg: String) {
            // Buat objek Toast dengan pesan dan durasi yang diinginkan
            val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            // Tampilkan toast
            toast.show()
        }

        fun swal( context: Context, msg : String, title : String = "", param_opt : String = "normal" ){
            /*
            normal -> Gak ada icon, cuman modal biasa
            success -> Ada icon successnya
            danger -> Ada icon gagalnya
            warning -> ada icon peringatannya
             */

            var sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE)

            when (param_opt) {
                "success" -> {
                    sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                }
                "warning" -> {
                    sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                }
                "danger" -> {
                    sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                }
            }

            sweetAlertDialog.titleText = title
            sweetAlertDialog.contentText = msg
            sweetAlertDialog.confirmText = "OK"
            sweetAlertDialog.setConfirmClickListener { sDialog ->
                // Aksi yang akan diambil ketika tombol OK diklik
                sDialog.dismissWithAnimation()
            }

            sweetAlertDialog.show()
        }
    }
}
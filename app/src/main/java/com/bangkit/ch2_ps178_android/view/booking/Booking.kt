package com.bangkit.ch2_ps178_android.view.booking

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.model.BaseModel

class Booking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)






        //Modal info pem
        var modalInfoPem = ModalInfoPem(this)
        var col_info_pem : LinearLayout = findViewById(R.id.col_info_pem)
        col_info_pem.setOnClickListener{
            modalInfoPem.show()
        }

        //Modal catatan
        var modalCatatan = ModalCatatan(this)
        var layout_catatan : LinearLayout = findViewById(R.id.layout_catatan)
        layout_catatan.setOnClickListener{
            modalCatatan.show()
        }

        //Modal pesan syarat
        var modalSyarat = ModalSyarat(this)
        var btn_pesan : Button = findViewById(R.id.btn_pesan)
        btn_pesan.setOnClickListener{
            modalSyarat.show()
        }


    }










//    private fun modal_catatan() {
//        val dialog = Dialog(this)
//
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.modal_catatan_layout)
//
//        setting_modal(dialog)
//
//        // Tampilkan dialog
//        dialog.show()
//    }
//
//    private fun modal_syarat() {
//        val dialog = Dialog(this)
//
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.modal_syarat_layout )
//
//        setting_modal(dialog)
//
//        // Tampilkan dialog
//        dialog.show()
//    }

}


open class ModalSetting{


    lateinit var dialog: Dialog
    lateinit var context: Context

    open fun setting_modal( dialog : Dialog ){

        var bgTransprant: ColorDrawable = ColorDrawable(Color.TRANSPARENT)

        //Atur warna backdropnya
        //window?.setBackgroundDrawable(bgTransprant)
        //Ini merupakan constrain layout di ui
        var container_modal = dialog.window

        val layoutParams = container_modal?.attributes
        layoutParams?.dimAmount = 0.1f // Atur nilai transparansi di sini (0.0f hingga 1.0f)
        container_modal?.attributes = layoutParams

        // Atur warna background modal dialog
        container_modal?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // Atur animasi agar dialog muncul dari bawah
        container_modal?.attributes?.windowAnimations = R.style.DialogAnimation
        // Set contentPadding menjadi nol untuk menghilangkan padding
        container_modal?.decorView?.setPadding(0, 0, 0, 0)
        // Atur lebar dan tinggi dialog agar sesuai kebutuhan

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        container_modal?.setLayout(width, height)

    }

    fun show(){
        // Tampilkan dialog
        dialog.show()
    }
}

class ModalInfoPem( context: Context ) : ModalSetting() {


    init{
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.modal_infopem_layout)

        setting_modal(dialog)
    }


}


class ModalSyarat( context: Context ) : ModalSetting() {



    init{
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.modal_syarat_layout)

        setting_modal(dialog)
    }

}

class ModalCatatan( context: Context ) : ModalSetting() {

    init{
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.modal_catatan_layout)

        setting_modal(dialog)
    }

}





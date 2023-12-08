package com.bangkit.ch2_ps178_android.view.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.ch2_ps178_android.R
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bangkit.ch2_ps178_android.data.model.BaseModel

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        BaseModel.swal(this, "Hello World")
    }
}
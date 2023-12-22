package com.bangkit.ch2_ps178_android.data.model

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow

class BaseModel {




    companion object {
        var BASE_URL : String
        var paramName : String

        init{
            this.BASE_URL = "https://silent-fuze-400506.et.r.appspot.com/"
            this.paramName = "data_paramObj"
        }




        fun getImg( data_obj : MainAdapterRow ) : String {
            var url : String = ""
            if ( data_obj.jenisSepakbola == "1" ){
                url = "https://magetan.satujam.com/wp-content/uploads/2016/02/permukaan-lapangan-sepak-bola.jpg?strip=all&lossy=1&resize=800%2C400&ssl=1"
            }else if( data_obj.jenisBadminton == "1" ){
                url = "https://img2.beritasatu.com/cache/beritasatu/910x580-2/1516702369.jpg"
            }else if( data_obj.jenisTenis == "1" ){
                url = "https://assets-a1.kompasiana.com/items/album/2018/02/19/p-20180213-172447-5a8a8e50caf7db670d5a0ca2.jpg"
            }else if( data_obj.jenisVoli== "1" ){
                url = "https://cdn.rri.co.id/berita/81/images/1691656286416-IMG-20230810-WA0125/1691656286416-IMG-20230810-WA0125.jpg"
            }else if( data_obj.jenisBasket == "1" ){
                url = "https://asset-2.tstatic.net/tribunnews/foto/bank/images/persiapan-timnas-basket-dan-penyelenggaraan-fiba-asia-cup.jpg"
            }else if( data_obj.jenisFutsal == "1" ){
                url = "https://garudasports.co.id/wp-content/uploads/2020/02/Jenis-Jenis-Lapangan-Futsal.jpg"
            }

            return url
        }
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
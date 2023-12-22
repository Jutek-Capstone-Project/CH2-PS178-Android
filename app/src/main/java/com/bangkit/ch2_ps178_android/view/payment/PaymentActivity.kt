package com.bangkit.ch2_ps178_android.view.payment

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import com.bangkit.ch2_ps178_android.data.model.BaseModel
import com.bangkit.ch2_ps178_android.databinding.ActivityPaymentBinding
import com.bangkit.ch2_ps178_android.view.booking.InputModal
import com.bangkit.ch2_ps178_android.view.booking.ModalSyarat
import com.bangkit.ch2_ps178_android.view.booking.PassingData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.textfield.TextInputEditText

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()


        val receivedIntent = intent
        if (receivedIntent != null && receivedIntent.hasExtra("data_paramObj")) {
            val data_obj = receivedIntent.getParcelableExtra<PassingData>("data_paramObj")

            // Gunakan data yang diterima di sini
            if (data_obj != null) {


                var data_row : MainAdapterRow = data_obj.mainAdapterRow


                var img_el : ImageView = findViewById(R.id.gambar)
                var img_url = BaseModel.getImg( data_row )
                Glide.with(this)
                    .load(img_url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // Atur ke DiskCacheStrategy.NONE jika Anda tidak ingin menyimpan cache
                    .into(img_el)

                set_txtContent(findViewById(R.id.tv_nama_lapangan), data_row.name)
                set_txtContent(findViewById(R.id.lokasi), data_row.kecamatan)



                set_txtContent(findViewById(R.id.jam_check_in), data_obj.tanggal)
                set_txtContent(findViewById(R.id.jam_check_out), data_obj.checkOut)

                set_txtContent(findViewById(R.id.tanggal_booking), data_obj.checkIn)


                set_txtContent(findViewById(R.id.biaya_lapangan), "Rp " + data_row.price)

                var total_harga = data_row.price.toInt() + 4000
                set_txtContent(findViewById(R.id.total_pembayaran),  "Rp " + total_harga.toString())




            }
        }

    }

    private fun setupActionBar() {
        val toolbar: Toolbar = findViewById(R.id.tb_payment_back)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.orange700)))
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun set_txtContent(obj_el : TextView, data :String  ){
        obj_el.text = data
    }
}
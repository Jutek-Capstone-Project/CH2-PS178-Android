package com.bangkit.ch2_ps178_android.view.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import com.bangkit.ch2_ps178_android.databinding.ActivityDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedIntent = intent
        if (receivedIntent != null && receivedIntent.hasExtra("data_detail")) {
            val data = receivedIntent.getParcelableExtra<MainAdapterRow>("data_detail")

            // Gunakan data yang diterima di sini
            if (data != null) {
                // Lakukan sesuatu dengan data yang diterima

                //untuk gambar
                var img_el : ImageView = findViewById(R.id.iv_lapangan)

                //untuk data text
                set_txtContent(findViewById(R.id.tv_nama_lapangan), data.name)
                set_txtContent(findViewById(R.id.tv_rating), data.rating)
                set_txtContent(findViewById(R.id.daerah), data.kecamatan)
                set_txtContent(findViewById(R.id.harga),  "Rp " + data.price)
//                set_txtContent(findViewById(R.id.tv_rating), data.price)

                //Untuk data icon
                set_icon( findViewById(R.id.col_wifi), data.fasilitasWifi )
                set_icon( findViewById(R.id.col_toilet), data.fasilitasWC )
                set_icon( findViewById(R.id.col_parkir_motor), data.fasilitasParkirMotor )
                set_icon( findViewById(R.id.col_parkir_mobil), data.fasilitasParkirMobil )
                set_icon( findViewById(R.id.col_mushola), data.fasilitasMushola )

            }
        }
        var tombol_back = findViewById<FloatingActionButton>(R.id.fab_back)
        tombol_back.setOnClickListener {
            onBackPressed()
        }
    }

    fun set_txtContent( obj_el : TextView, data :String  ){
        obj_el.text = data
    }

    fun set_icon( obj_el : CardView, data : String ){
        if ( data == "1" ){
            // Mengatur CardView menjadi terlihat
            obj_el.visibility = View.VISIBLE
        }else{
            // Mengatur CardView menjadi hilang
            obj_el.visibility = View.GONE
        }



    }

}
package com.bangkit.ch2_ps178_android.view.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.adapter.MainAdapter
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import com.bangkit.ch2_ps178_android.data.model.BaseModel
import com.bangkit.ch2_ps178_android.view.detail.DetailActivity

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Panggil loadContentList di sini untuk memuat data dan mengatur RecyclerView
        loadContentList()
    }

    private fun loadContentList() {
        // Recycleview data storys
        val recyclerView: RecyclerView = requireView().findViewById(R.id.data_list)
        val adapter = MainAdapter() { data_row, el_row ->
            // Event ketika item di RecyclerView di klik
            // Data click adalah data row pada list dari setiap index
            // el_story elemen card sesuai dengan index berbanding lurus dengan data click
            BaseModel.toast(requireContext(), data_row.judul)
            direct_event( "contoh parameter id data")
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Data sementara
        val gambar = "https://images.tokopedia.net/blog-tokopedia-com/uploads/2020/11/lapangan-sepak-bola.jpg"

        var listData: List<MainAdapterRow> = listOf(
            MainAdapterRow(gambar, "Judul", "48km", "20.000")
        )
        for( i in 1..5  ){

            var judul = "Judul" + i.toString()
            var jarak = "Jarak" + i.toString()
            var harga =  "Rp" + i.toString() + "000"
            listData = listData + MainAdapterRow( gambar,  judul, jarak, harga )
        }


        adapter.submitList(listData)
    }

    fun direct_event( param : String ){
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("param", param)
        startActivity(intent)
    }
}
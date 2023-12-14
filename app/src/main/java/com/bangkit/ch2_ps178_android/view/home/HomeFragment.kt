package com.bangkit.ch2_ps178_android.view.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.adapter.AdapterStatis
import com.bangkit.ch2_ps178_android.data.adapter.MainAdapter
import com.bangkit.ch2_ps178_android.data.api.MainApi
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import com.bangkit.ch2_ps178_android.data.model.BaseModel
import com.bangkit.ch2_ps178_android.data.model.MainModel
import com.bangkit.ch2_ps178_android.view.detail.DetailActivity
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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


        loadContentNonPaging()

//        loadContentList()
    }


    private fun loadContentList() {
//        Ini data content yang pake paging, cuman yaa gituh


        // Panggil loadContentList di sini untuk memuat data dan mengatur RecyclerView
        // Recycleview data storys
        val recyclerView: RecyclerView = requireView().findViewById(R.id.data_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MainAdapter() { data_row, el_row ->
            // Event ketika item di RecyclerView di klik
            // Data click adalah data row pada list dari setiap index
            // el_story elemen card sesuai dengan index berbanding lurus dengan data click
            BaseModel.toast(requireContext(), data_row.name)
            direct_event( "contoh parameter id data")
        }

        //Memasukkan data dari view model API
        val main_viewModel = ViewModelProvider(this).get(MainModel::class.java)
        main_viewModel.set_data() //Mengisi data untuk live data
        main_viewModel.Data_mainPaging.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                adapter.submitData(it)
            }

        })

    }

    fun loadContentNonPaging(){
        val retrofit = Retrofit.Builder()
            .baseUrl( BaseModel.BASE_URL ) // Ganti dengan base URL API Anda
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(MainApi::class.java)
        val call = apiService.get_data()
        call.enqueue(object : Callback<List<MainAdapterRow>> {
            override fun onResponse(
                call: Call<List<MainAdapterRow>>,
                response: Response<List<MainAdapterRow>>
            ) {
                if (response.isSuccessful) {
                    val data: List<MainAdapterRow>? = response.body()

                    // Inisialisasi adapter
                    val adapterStatis = AdapterStatis { data_row, cardView ->
                        // Tangani klik item di sini
                        // mainAdapterRowStatis adalah objek data dari item yang diklik
                        // cardView adalah elemen CardView yang diklik
                        direct_event_obj( data_row )

                    }

                    // Inisialisasi RecyclerView
                    val recyclerView: RecyclerView = requireView().findViewById(R.id.data_list)

                    // Atur layout manager dan adapter untuk RecyclerView
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapterStatis

                    // Kemudian, set data ke dalam adapter
                    val dataList = // ambil data dari mana pun
                        data?.let { adapterStatis.submitList(it) }
                    BaseModel.swal( requireContext(),"Data ada")
                    // Lakukan sesuatu dengan data
                } else {
                    // Handle kesalahan
                }
            }

            override fun onFailure(call: Call<List<MainAdapterRow>>, t: Throwable) {
                // Handle kesalahan ketika gagal melakukan panggilan API
            }
        })
    }

    fun direct_event( param : String ){
//        Ini ngirim data dengan anggapan data ke detail diambil dari link by id
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("param", param)
        startActivity(intent)
    }

    fun direct_event_obj( data_row_obj : MainAdapterRow ){


//        BaseModel.swal(requireContext(), "s")
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("data_detail", data_row_obj)
        startActivity(intent)
    }
}
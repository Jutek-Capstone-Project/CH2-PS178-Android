package com.bangkit.ch2_ps178_android.view.home

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
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapter_row
import com.bangkit.ch2_ps178_android.data.model.Base_model
import com.bangkit.ch2_ps178_android.view.detail_lapangan.Detail_lapangan
import com.bangkit.ch2_ps178_android.view.main.MainActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Panggil loadContentList di sini untuk memuat data dan mengatur RecyclerView
        loadContentList()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun loadContentList() {
        // Recycleview data storys
        val recyclerView: RecyclerView = requireView().findViewById(R.id.data_list)
        val adapter = MainAdapter() { data_row, el_row ->
            // Event ketika item di RecyclerView di klik
            // Data click adalah data row pada list dari setiap index
            // el_story elemen card sesuai dengan index berbanding lurus dengan data click
            Base_model.toast(requireContext(), data_row.judul)
            direct_event( "contoh parameter id data")
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Data sementara
        val gambar = "https://images.tokopedia.net/blog-tokopedia-com/uploads/2020/11/lapangan-sepak-bola.jpg"

        var listData: List<MainAdapter_row> = listOf(
            MainAdapter_row(gambar, "Judul", "48km", "20.000")
        )
        for( i in 1..5  ){

            var judul = "Judul" + i.toString()
            var jarak = "Jarak" + i.toString()
            var harga =  "Rp" + i.toString() + "000"
            listData = listData + MainAdapter_row( gambar,  judul, jarak, harga )
        }


        adapter.submitList(listData)
    }

    fun direct_event( param : String ){
        val intent = Intent(requireActivity(), Detail_lapangan::class.java)
        intent.putExtra("param", param)
        startActivity(intent)
    }
}
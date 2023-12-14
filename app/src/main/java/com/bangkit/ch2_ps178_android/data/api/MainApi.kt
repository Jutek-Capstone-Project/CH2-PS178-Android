package com.bangkit.ch2_ps178_android.data.api


import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import retrofit2.Call
import retrofit2.http.GET

interface MainApi {
    @GET("api/data")
    fun get_data(): Call<List<MainAdapterRow>>

}



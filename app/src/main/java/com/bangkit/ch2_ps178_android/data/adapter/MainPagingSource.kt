package com.bangkit.ch2_ps178_android.data.adapter

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bangkit.ch2_ps178_android.data.api.MainApi
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterReq
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import retrofit2.HttpException


class MainPagingSource(private val apiService: MainApi) : PagingSource<Int, MainAdapterRow>() {

    companion object {
        const val INITIAL_PAGE_INDEX = 1

        fun snapshot(items: List<MainAdapterRow>): PagingData<MainAdapterRow> {
            return PagingData.from(items)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MainAdapterRow> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX


            val response = apiService.postData(
                page = position,
                pageSize = params.loadSize,
                lat = -6.175392,
                long = 106.827153,
                input_field = "sepak bola"
            )
            val data = response.body() ?: emptyList()

            LoadResult.Page(
                data = data,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (data.isEmpty()) null else position + 1
            )


        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MainAdapterRow>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}
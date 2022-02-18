package com.practies.photogallery.paging

import android.util.Log
import android.widget.Toast
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.practies.photogallery.api.ApiService
import com.practies.photogallery.model.ApiData
import com.practies.photogallery.model.ImageData
import java.lang.Exception
import java.security.spec.ECField

class ImagePagingSource(private val apiService: ApiService): PagingSource<Int, ImageData>(){


    override fun getRefreshKey(state: PagingState<Int, ImageData>): Int? {
     return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageData> {

        return  try {
            val currentPage=params.key?:1
            val response=apiService.getImages(currentPage)
            Log.i("TAG", response.body()!!.imageResult[2].toString())

            val data= response.body()?.imageResult?: emptyList()
            val responseData= mutableListOf<ImageData>()

           responseData.addAll(data)

            LoadResult.Page(
                data=responseData,
                prevKey = if (currentPage ==1) null else  -1,
                nextKey = currentPage.plus(1)
            )




        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }


}
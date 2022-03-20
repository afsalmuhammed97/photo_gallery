package com.practies.photogallery.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.practies.photogallery.api.ApiService
import com.practies.photogallery.model.ImageData

class ImagePagingSource
    (private val apiService: ApiService): PagingSource<Int, ImageData>(){


    override fun getRefreshKey(state: PagingState<Int, ImageData>): Int? {
     return  state.anchorPosition?.let { anchorPosition ->
         state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
             ?:state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

     }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageData> {
        val pageIndex=params.key?:1
        return  try {

                val result=apiService.getImages(pageIndex)

                val data=result.body()?:emptyList()

              val responseData= mutableListOf<ImageData>()

                        responseData.addAll(data)

                      Log.i("TAG2",responseData.toString())



            val nextKey= if (data.isEmpty()) {
                null
            }else{
                pageIndex+(params.loadSize/30)
            }


            LoadResult.Page(

                data= responseData,
                prevKey = if (pageIndex ==1) null else  -1,
                nextKey = nextKey
            )




        }catch (e:Exception){

            Log.e("Exceptions",e.message.toString())
            LoadResult.Error(e)

        }
    }


}
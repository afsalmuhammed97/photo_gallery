package com.practies.photogallery.api

import com.practies.photogallery.Utill.Constants.END_POINT
import com.practies.photogallery.model.ApiData
import com.practies.photogallery.model.ImageData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//END_POINT
@GET(END_POINT)
suspend fun getImages( @Query( "page")page:Int): Response<ApiData>
}
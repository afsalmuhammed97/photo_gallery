package com.practies.photogallery.Di

import com.practies.photogallery.Utill.Constants.BASE_URL
import com.practies.photogallery.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   @Provides
    fun provideBaseUrl()=BASE_URL

    @Provides
    @Singleton
    fun provideApiInstance(BASE_URL:String):ApiService=

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService ::class.java)

}
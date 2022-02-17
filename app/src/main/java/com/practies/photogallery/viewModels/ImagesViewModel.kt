package com.practies.photogallery.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.practies.photogallery.api.ApiService
import com.practies.photogallery.paging.ImagePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ImagesViewModel @Inject constructor(private val apiService: ApiService):ViewModel(){

    val listData=Pager(PagingConfig(pageSize = 1)){
        ImagePagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}
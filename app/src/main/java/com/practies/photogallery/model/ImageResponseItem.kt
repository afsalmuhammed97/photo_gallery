package com.practies.photogallery.model

data class ImageResponseItem(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)
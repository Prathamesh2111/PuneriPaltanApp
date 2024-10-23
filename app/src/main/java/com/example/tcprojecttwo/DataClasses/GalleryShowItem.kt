package com.example.tcprojecttwo.DataClasses

data class GalleryShowItem(
    val cat_name: String,
    val id: String,
    val main_image: String,
    val match_images: List<String>,
    val name: String
)
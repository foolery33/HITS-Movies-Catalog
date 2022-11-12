package com.example.myapplication.network.movie

@kotlinx.serialization.Serializable
data class PageInfoModel(
    val pageSize: Int,
    val pageCount: Int,
    val currentPage: Int
)

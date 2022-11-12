package com.example.myapplication.network.movie

@kotlinx.serialization.Serializable
data class MoviesPagedListModel(
    val movies: List<MovieElementModel>?,
    val pageInfo: PageInfoModel
)

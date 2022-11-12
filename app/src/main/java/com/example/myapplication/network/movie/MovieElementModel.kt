package com.example.myapplication.network.movie

import com.example.myapplication.network.favourite_movies.GenreModel

@kotlinx.serialization.Serializable
data class MovieElementModel(
    val id: String,
    val name: String?,
    val poster: String?,
    val year: Int,
    val country: String?,
    val genres: List<GenreModel>?,
    val reviews: List<ReviewShortModel>?
)

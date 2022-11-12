package com.example.myapplication.network.favourite_movies

@kotlinx.serialization.Serializable
data class MovieModel(
    val id: String,
    val name: String?,
    val poster: String?,
    val year: Int,
    val country: String?,
    val genres: List<GenreModel>?,
    val reviews: List<ReviewShortModel>?
)
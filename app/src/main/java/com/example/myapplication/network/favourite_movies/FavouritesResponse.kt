package com.example.myapplication.network.favourite_movies

@kotlinx.serialization.Serializable
data class FavouritesResponse(
    val id: String,
    val name: String,
    val poster: String,
    val year: Int,
    val country: String,
    val genres: List<GenreModel>,
    val reviews: List<ReviewModel>
)
package com.example.myapplication.network.favourite_movies

@kotlinx.serialization.Serializable
data class FavouritesResponse(
    val movies: List<MovieModel>
)
package com.example.myapplication.network.favourite_movies

@kotlinx.serialization.Serializable
data class FavouriteMovieList(
    val movies: List<MovieModel>
)
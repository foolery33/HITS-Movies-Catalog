package com.example.myapplication.network.favourite_movies

@kotlinx.serialization.Serializable
data class ReviewShortModel(
    val id: String,
    val rating: Int
)

package com.example.myapplication.network.favourite_movies

@kotlinx.serialization.Serializable
data class ReviewModel(
    val id: String,
    val rating: Int
)

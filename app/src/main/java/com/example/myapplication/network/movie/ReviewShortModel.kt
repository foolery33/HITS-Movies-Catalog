package com.example.myapplication.network.movie

@kotlinx.serialization.Serializable
data class ReviewShortModel(
    val id: String,
    val rating: Int
)

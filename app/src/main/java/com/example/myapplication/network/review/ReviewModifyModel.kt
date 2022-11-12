package com.example.myapplication.network.review

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ReviewModifyModel(
    @SerialName("reviewText")
    val reviewText: String,
    @SerialName("rating")
    val rating: Int,
    @SerialName("isAnonymous")
    val isAnonymous: Boolean
)

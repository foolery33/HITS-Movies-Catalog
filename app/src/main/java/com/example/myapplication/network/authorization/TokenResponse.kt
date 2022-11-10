package com.example.myapplication.network.authorization

@kotlinx.serialization.Serializable
data class TokenResponse(
    val token: String
)
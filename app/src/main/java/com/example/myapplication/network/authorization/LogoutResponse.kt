package com.example.myapplication.network.authorization

@kotlinx.serialization.Serializable
data class LogoutResponse(
    val token: String,
    val message: String
)

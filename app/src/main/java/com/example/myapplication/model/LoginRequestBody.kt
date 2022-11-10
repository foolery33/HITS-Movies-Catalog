package com.example.myapplication.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class LoginRequestBody(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)
package com.example.myapplication.network.authorization

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RegisterRequestBody(
    @SerialName("userName")
    val userName: String,
    @SerialName("name")
    val name: String,
    @SerialName("password")
    val password: String,
    @SerialName("email")
    val email: String,
    @SerialName("birthDate")
    val birthDate: String,
    @SerialName("gender")
    val gender: Int
)
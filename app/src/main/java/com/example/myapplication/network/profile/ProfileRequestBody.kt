package com.example.myapplication.network.profile

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ProfileRequestBody(
    @SerialName("id")
    val id: String,
    @SerialName("nickName")
    val nickName: String,
    @SerialName("email")
    val email: String,
    @SerialName("avatarLink")
    val avatarLink: String,
    @SerialName("name")
    val name: String,
    @SerialName("birthDate")
    val birthDate: String,
    @SerialName("gender")
    val gender: Int
)

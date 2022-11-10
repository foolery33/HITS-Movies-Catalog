package com.example.myapplication.network.profile

@kotlinx.serialization.Serializable
data class ProfileResponse(
    val id: String,
    val nickName: String?,
    val email: String,
    val avatarLink: String?,
    val name: String,
    val birthDate: String,
    val gender: Int
)
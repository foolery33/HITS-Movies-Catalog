package com.example.myapplication.network.movie

@kotlinx.serialization.Serializable
data class UserShortModel(
    val userId: String,
    val nickName: String?,
    val avatar: String?
)

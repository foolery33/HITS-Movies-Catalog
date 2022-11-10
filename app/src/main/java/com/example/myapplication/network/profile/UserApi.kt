package com.example.myapplication.network.profile

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface UserApi {
    @GET("/api/account/profile")
    suspend fun getProfile(@Header("Authorization") token: String): ProfileResponse
    @PUT("/api/account/profile")
    suspend fun setProfile(@Header("Authorization") token: String, @Body body: ProfileRequestBody)
}
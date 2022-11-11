package com.example.myapplication.network.authorization

import com.example.myapplication.model.LoginRequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/account/register")
    suspend fun register(@Body body: RegisterRequestBody): TokenResponse
    @POST("/api/account/login")
    suspend fun login(@Body body: LoginRequestBody): TokenResponse
    @POST("/api/account/logout")
    suspend fun logout(): LogoutResponse
}
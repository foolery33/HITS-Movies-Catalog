package com.example.myapplication.domain.sign_up_screen.repository

import android.content.Context
import com.example.myapplication.model.LoginRequestBody
import com.example.myapplication.network.authorization.LogoutResponse
import com.example.myapplication.network.authorization.RegisterRequestBody
import com.example.myapplication.network.authorization.TokenResponse

interface AuthRepository {

    suspend fun register(body: RegisterRequestBody): TokenResponse

    suspend fun login(body: LoginRequestBody): TokenResponse

    suspend fun logout(): LogoutResponse

    fun saveUserToken(context: Context, tokenResponse: TokenResponse)

    fun getUserToken(context: Context): TokenResponse

}
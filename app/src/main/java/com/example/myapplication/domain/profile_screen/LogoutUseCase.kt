package com.example.myapplication.domain.profile_screen

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.network.authorization.TokenResponse

class LogoutUseCase {

    private val repository = Repositories.authRepository

    suspend fun logout(context: Context) {
        repository.saveUserToken(context = context, tokenResponse = TokenResponse(token = repository.logout().token))
    }

}
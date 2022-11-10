package com.example.myapplication.domain.sign_in_screen

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.model.LoginRequestBody

class LoginUseCase {

    private val repository = Repositories.authRepository

    suspend fun login(
        username: String,
        password: String,
        context: Context
    ) {
        val loginRequestBody = LoginRequestBody(
            username = username,
            password = password
        )
        repository.saveUserToken(context, repository.login(loginRequestBody))
    }

}
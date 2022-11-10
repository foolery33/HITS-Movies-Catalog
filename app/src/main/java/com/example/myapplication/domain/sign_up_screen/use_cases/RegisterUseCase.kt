package com.example.myapplication.domain.sign_up_screen.use_cases

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.network.authorization.RegisterRequestBody

class RegisterUseCase {

    private val repository = Repositories.authRepository

    suspend fun registration(
        userName: String,
        name: String,
        password: String,
        email: String,
        birthDate: String,
        gender: Int,
        context: Context
    ) {
        val registerRequestBody = RegisterRequestBody(
            userName = userName,
            name = name,
            password = password,
            email = email,
            birthDate = birthDate,
            gender = gender
        )
        repository.saveUserToken(context = context, repository.register(registerRequestBody))
    }

}
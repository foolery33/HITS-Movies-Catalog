package com.example.myapplication.data

import com.example.myapplication.domain.sign_up_screen.repository.AuthRepository
import com.example.myapplication.domain.sign_up_screen.repository.UserRepository
import com.example.myapplication.network.authorization.AuthRepositoryImpl
import com.example.myapplication.network.profile.UserRepositoryImpl

object Repositories {
    val authRepository: AuthRepository by lazy { AuthRepositoryImpl() }
    val userRepository: UserRepository by lazy { UserRepositoryImpl() }
}
package com.example.myapplication.viewmodel.sign_in_screen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.domain.sign_in_screen.LoginUseCase

class SignInScreenState {
    val loginData = mutableStateOf("")
    val passwordData = mutableStateOf("")

    val areFilledFields: Boolean
    get() = loginData.value.isNotEmpty() && passwordData.value.isNotEmpty()

    private val loginUseCase = LoginUseCase()

    suspend fun onClickLogin(context: Context) {
        loginUseCase.login(
            username = loginData.value,
            password = passwordData.value,
            context = context
        )
    }
}

@Composable
fun rememberSignInScreenState() = remember { SignInScreenState() }
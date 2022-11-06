package com.example.myapplication.viewmodel.sign_in_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class SignInScreenState {
    val loginData = mutableStateOf("")
    val passwordData = mutableStateOf("")

    val areFilledFields: Boolean
    get() = loginData.value.isNotEmpty() && passwordData.value.isNotEmpty()

}

@Composable
fun rememberSignInScreenState() = remember { SignInScreenState() }
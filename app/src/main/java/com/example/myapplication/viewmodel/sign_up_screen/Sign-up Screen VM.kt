package com.example.myapplication.viewmodel.sign_up_screen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.domain.sign_up_screen.use_cases.DateConverterUseCase
import com.example.myapplication.domain.sign_up_screen.use_cases.RegisterUseCase

class SignUpScreenState {


    val loginData = mutableStateOf("")
    val emailData = mutableStateOf("")
    val nameData = mutableStateOf("")
    val passwordData = mutableStateOf("")
    val confirmPasswordData = mutableStateOf("")
    val maleSexData = mutableStateOf(false)
    val femaleSexData = mutableStateOf(false)
    val dateData = DatePickerState("")
    val areFilledFields: Boolean
        get() = dateData.dateData.value.isNotEmpty() && loginData.value.isNotEmpty() && emailData.value.isNotEmpty() && nameData.value.isNotEmpty() && passwordData.value.isNotEmpty() && confirmPasswordData.value.isNotEmpty() && (maleSexData.value || femaleSexData.value)

    private val registerUseCase = RegisterUseCase()

    suspend fun onClickRegister(context: Context) {
        registerUseCase.registration(
            userName = loginData.value,
            name = nameData.value,
            password = passwordData.value,
            email = emailData.value,
            birthDate = DateConverterUseCase().toJsonFormat(dateData.dateData.value.replace("/", ".")),
            gender = if (maleSexData.value) 0 else 1,
            context = context
        )
    }
}

@Composable
fun rememberSignUpScreenState() = remember {
    SignUpScreenState()
}
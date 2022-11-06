package com.example.myapplication.viewmodel.sign_up_screen

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

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
}

@Composable
fun rememberSignUpScreenState() = remember {
        SignUpScreenState()
}
package com.example.myapplication.viewmodel.sign_up_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class DatePickerState(defaultValue: String) {
    val dateData = mutableStateOf(defaultValue)
}

@Composable
fun rememberDatePickerState(defaultValue: String) = remember { DatePickerState(defaultValue) }
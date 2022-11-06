package com.example.myapplication.viewmodel.profile_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.viewmodel.sign_up_screen.DatePickerState

class ProfileScreenState {

    val emailData = mutableStateOf("test@example.com")
    val linkData = mutableStateOf("https://vk.com/chdMpx")
    val nameData = mutableStateOf("Тест Тестович")
    val maleSexData = mutableStateOf(false)
    val femaleSexData = mutableStateOf(true)

    val dateData = DatePickerState("01.01.2022")

    val areFilledFields: Boolean
        get() = dateData.dateData.value.isNotEmpty() && emailData.value.isNotEmpty() && linkData.value.isNotEmpty() && nameData.value.isNotEmpty() && (femaleSexData.value || maleSexData.value)

}

@Composable
fun rememberProfileScreenState() = remember {
    ProfileScreenState()
}
package com.example.myapplication.viewmodel.profile_screen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.domain.profile_screen.DateReverseConverter
import com.example.myapplication.domain.profile_screen.GetProfileUseCase
import com.example.myapplication.network.profile.ProfileResponse
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

    private val userUseCase = GetProfileUseCase()

    suspend fun onClickProfile(context: Context) {
        val response: ProfileResponse = userUseCase.getProfile(context = context)
        emailData.value = response.email
        if(response.avatarLink == null) {
            linkData.value = "https:vk.com/foolery33"
        }
        else {
            linkData.value = response.avatarLink
        }
        nameData.value = response.name
        //dateData.dateData.value = DateReverseConverter().toProfileFormat(date = response.birthDate)
        dateData.dateData.value = response.birthDate
        if(response.gender == 0) {
            maleSexData.value = true
            femaleSexData.value = false
        }
    }
}

@Composable
fun rememberProfileScreenState() = remember {
    ProfileScreenState()
}
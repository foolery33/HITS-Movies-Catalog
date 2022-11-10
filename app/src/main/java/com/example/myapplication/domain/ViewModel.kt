package com.example.myapplication.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.viewmodel.profile_screen.ProfileScreenState

object ViewModel {
    val profileScreen: ProfileScreenState = ProfileScreenState()
}
package com.example.myapplication.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.viewmodel.main_screen.MainScreenState
import com.example.myapplication.viewmodel.profile_screen.ProfileScreenState
import com.example.myapplication.viewmodel.sign_in_screen.SignInScreenState
import com.example.myapplication.viewmodel.sign_up_screen.SignUpScreenState

object ViewModel {
    val profileScreen: ProfileScreenState = ProfileScreenState()
    val signInScreen: SignInScreenState = SignInScreenState()
    val signUpScreen: SignUpScreenState = SignUpScreenState()
    val mainScreen: MainScreenState = MainScreenState()
}
package com.example.myapplication.domain

import com.example.myapplication.viewmodel.main_screen.MainScreenState
import com.example.myapplication.viewmodel.movie_screen.MovieScreenState
import com.example.myapplication.viewmodel.profile_screen.ProfileScreenState
import com.example.myapplication.viewmodel.sign_in_screen.SignInScreenState
import com.example.myapplication.viewmodel.sign_up_screen.SignUpScreenState

object ViewModels {
    val profileScreen: ProfileScreenState = ProfileScreenState()
    val signInScreen: SignInScreenState = SignInScreenState()
    val signUpScreen: SignUpScreenState = SignUpScreenState()
    val mainScreen: MainScreenState = MainScreenState()
    val movieScreen: MovieScreenState = MovieScreenState()
}
package com.example.myapplication.viewmodel.sign_in_screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.data.Repositories
import com.example.myapplication.domain.general_use_cases.MakeToastUseCase
import com.example.myapplication.domain.main_screen.use_cases.GetFavouritesUseCase
import com.example.myapplication.domain.sign_in_screen.use_cases.LoginUseCase
import com.example.myapplication.network.authorization.TokenResponse
import com.example.myapplication.screen.destinations.MainScreenDestination
import com.example.myapplication.screen.destinations.SignInScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class SignInScreenState {

    val loginData = mutableStateOf("")
    val passwordData = mutableStateOf("")

    val areFilledFields: Boolean
    get() = loginData.value.isNotEmpty() && passwordData.value.isNotEmpty()

    suspend fun onClickLogin(context: Context, navigator: DestinationsNavigator) {
        try {
            Repositories.authRepository.saveUserToken(context, TokenResponse(""))
            LoginUseCase().login(
                username = loginData.value,
                password = passwordData.value,
                context = context
            )
            navigator.popBackStack(SignInScreenDestination, true)
            navigator.navigate(MainScreenDestination)
        } catch (e: Exception) {
            MakeToastUseCase().show(context, "Неправильный логин и/или пароль")
            Log.i("errorList", e.message.toString())
        }
    }
}

@Composable
fun rememberSignInScreenState() = remember { SignInScreenState() }
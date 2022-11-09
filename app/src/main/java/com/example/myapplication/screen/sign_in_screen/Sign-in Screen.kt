package com.example.myapplication.screen.sign_in_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.screen.destinations.MainScreenDestination
import com.example.myapplication.screen.destinations.SignUpScreenDestination
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.ButtonView
import com.example.myapplication.view.OutlinedButtonView
import com.example.myapplication.view.OutlinedTextFieldView
import com.example.myapplication.viewmodel.sign_in_screen.rememberSignInScreenState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

val loginButtonTopPadding = 48.dp
val defaultPadding = 16.dp
val doubleDefaultPadding = 16.dp
val halfDefaultPadding = defaultPadding / 2

@RootNavGraph(start = true)
@Destination
@Composable
fun SignInScreen(navigator: DestinationsNavigator) {

    val signInScreenState = rememberSignInScreenState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .padding(horizontal = defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.text_and_logo),
            contentDescription = "App logo and name",
            modifier = Modifier.padding(top = doubleDefaultPadding)
        )
        OutlinedTextFieldView(
            placeholderText = "Логин",
            data = signInScreenState.loginData,
            topPadding = loginButtonTopPadding,
            textDecoration = TextDecoration.None,
            visualTransformation = VisualTransformation.None
        )
        OutlinedTextFieldView(
            placeholderText = "Пароль",
            data = signInScreenState.passwordData,
            topPadding = defaultPadding,
            textDecoration = TextDecoration.None,
            visualTransformation = PasswordVisualTransformation()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = defaultPadding),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(halfDefaultPadding)) {
                OutlinedButtonView(
                    buttonText = "Войти",
                    areFilledFields = signInScreenState.areFilledFields,
                    paddingValues = PaddingValues(0.dp),
                ) {
                    navigator.navigate(MainScreenDestination)
                }
                ButtonView(
                    buttonText = "Регистрация",
                    paddingValues = PaddingValues(0.dp),
                    backgroundColor = backgroundColor,
                    textColor = textColor,
                ) {
                    navigator.navigate(SignUpScreenDestination)
                }
            }
        }
    }
}

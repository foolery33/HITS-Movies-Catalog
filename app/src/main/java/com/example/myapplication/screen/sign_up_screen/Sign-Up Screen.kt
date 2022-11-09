package com.example.myapplication.screen.sign_up_screen

import androidx.compose.foundation.*
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
import com.example.myapplication.domain.createDatePicker
import com.example.myapplication.screen.destinations.MainScreenDestination
import com.example.myapplication.screen.destinations.SignInScreenDestination
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.*
import com.example.myapplication.viewmodel.sign_up_screen.rememberSignUpScreenState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

val defaultPadding = 16.dp
val doubleDefaultPadding = defaultPadding * 2

@Destination
@Composable
fun SignUpScreen(navigator: DestinationsNavigator) {

    val signUpScreenState = rememberSignUpScreenState()

    Column(
        modifier = Modifier.fillMaxSize().background(color = backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sign_up_screen_logo),
            contentDescription = "Sign-up screen app logo and text",
            modifier = Modifier.padding(top = doubleDefaultPadding)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState())
                .padding(horizontal = defaultPadding)
        ) {
            SectionText(text = "Регистрация", paddingValues = PaddingValues(top = defaultPadding))
            OutlinedTextFieldView(
                placeholderText = "Логин",
                data = signUpScreenState.loginData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = VisualTransformation.None
            )
            OutlinedTextFieldView(
                placeholderText = "E-mail",
                data = signUpScreenState.emailData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = VisualTransformation.None
            )
            OutlinedTextFieldView(
                placeholderText = "Имя",
                data = signUpScreenState.nameData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = VisualTransformation.None
            )
            OutlinedTextFieldView(
                placeholderText = "Пароль",
                data = signUpScreenState.passwordData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedTextFieldView(
                placeholderText = "Подтвердите пароль",
                data = signUpScreenState.confirmPasswordData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = PasswordVisualTransformation()
            )
            DatePickerView(
                dateState = signUpScreenState.dateData,
                mDatePickerDialog = createDatePicker(data = signUpScreenState.dateData.dateData),
                topPadding = defaultPadding
            )
            SexPicker(
                maleSexData = signUpScreenState.maleSexData,
                femaleSexData = signUpScreenState.femaleSexData,
                topPadding = defaultPadding
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = defaultPadding),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column {
                    OutlinedButtonView(
                        buttonText = "Зарегистрироваться",
                        areFilledFields = signUpScreenState.areFilledFields,
                        paddingValues = defaultTopPadding
                    ) {
                        navigator.navigate(MainScreenDestination)
                    }
                    ButtonView(
                        buttonText = "У меня уже есть аккаунт",
                        paddingValues = commonButtonPaddings,
                        backgroundColor = backgroundColor,
                        textColor = textColor
                    ) {
                        navigator.navigate(SignInScreenDestination)
                    }
                }
            }
        }
    }
}
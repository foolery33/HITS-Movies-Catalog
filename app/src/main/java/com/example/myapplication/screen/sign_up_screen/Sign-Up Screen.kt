package com.example.myapplication.screen.sign_up_screen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.data.Repositories
import com.example.myapplication.domain.ViewModels
import com.example.myapplication.domain.createDatePicker
import com.example.myapplication.domain.general_use_cases.MakeToastUseCase
import com.example.myapplication.domain.sign_up_screen.use_cases.CheckPasswordsUseCase
import com.example.myapplication.screen.destinations.MainScreenDestination
import com.example.myapplication.screen.destinations.SignInScreenDestination
import com.example.myapplication.screen.destinations.SignUpScreenDestination
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val defaultPadding = 16.dp
val doubleDefaultPadding = defaultPadding * 2

@Destination
@Composable
fun SignUpScreen(navigator: DestinationsNavigator) {

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
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
                data = ViewModels.signUpScreen.loginData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = VisualTransformation.None
            )
            OutlinedTextFieldView(
                placeholderText = "E-mail",
                data = ViewModels.signUpScreen.emailData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = VisualTransformation.None
            )
            OutlinedTextFieldView(
                placeholderText = "Имя",
                data = ViewModels.signUpScreen.nameData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = VisualTransformation.None
            )
            OutlinedTextFieldView(
                placeholderText = "Пароль",
                data = ViewModels.signUpScreen.passwordData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedTextFieldView(
                placeholderText = "Подтвердите пароль",
                data = ViewModels.signUpScreen.confirmPasswordData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = PasswordVisualTransformation()
            )
            DatePickerView(
                dateState = ViewModels.signUpScreen.dateData,
                mDatePickerDialog = createDatePicker(data = ViewModels.signUpScreen.dateData.dateData),
                topPadding = defaultPadding
            )
            SexPicker(
                maleSexData = ViewModels.signUpScreen.maleSexData,
                femaleSexData = ViewModels.signUpScreen.femaleSexData,
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
                        areFilledFields = ViewModels.signUpScreen.areFilledFields,
                        paddingValues = defaultTopPadding
                    ) {
                        if (!CheckPasswordsUseCase().check(
                                ViewModels.signUpScreen.passwordData.value,
                                ViewModels.signUpScreen.confirmPasswordData.value
                            )
                        ) {
                            MakeToastUseCase().show(context, "Пароли не совпадают")
                        }
                        else {
                            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(ViewModels.signUpScreen.emailData.value)
                                    .matches()) {
                                MakeToastUseCase().show(context, "Некорректный e-mail")
                            }
                            else {
                                CoroutineScope(Dispatchers.Main).launch {
                                    ViewModels.signUpScreen.onClickRegister(context)
                                }
                                Log.i(
                                    "tokenValue",
                                    Repositories.authRepository.getUserToken(context).token
                                )
                                navigator.popBackStack(SignUpScreenDestination, true)
                                navigator.navigate(MainScreenDestination)
                            }
                        }
                    }
                    ButtonView(
                        buttonText = "У меня уже есть аккаунт",
                        paddingValues = commonButtonPaddings,
                        backgroundColor = backgroundColor,
                        textColor = textColor,
                        contentPadding = PaddingValues(6.dp)
                    ) {
                        navigator.navigate(SignInScreenDestination)
                    }
                }
            }
        }
    }
}
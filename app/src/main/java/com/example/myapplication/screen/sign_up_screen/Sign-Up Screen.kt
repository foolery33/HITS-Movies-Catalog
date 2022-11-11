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
import com.example.myapplication.domain.ViewModel
import com.example.myapplication.domain.createDatePicker
import com.example.myapplication.screen.destinations.MainScreenDestination
import com.example.myapplication.screen.destinations.SignInScreenDestination
import com.example.myapplication.screen.destinations.SignUpScreenDestination
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.*
import com.example.myapplication.viewmodel.sign_up_screen.rememberSignUpScreenState
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
                data = ViewModel.signUpScreen.loginData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = VisualTransformation.None
            )
            OutlinedTextFieldView(
                placeholderText = "E-mail",
                data = ViewModel.signUpScreen.emailData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = VisualTransformation.None
            )
            OutlinedTextFieldView(
                placeholderText = "Имя",
                data = ViewModel.signUpScreen.nameData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = VisualTransformation.None
            )
            OutlinedTextFieldView(
                placeholderText = "Пароль",
                data = ViewModel.signUpScreen.passwordData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedTextFieldView(
                placeholderText = "Подтвердите пароль",
                data = ViewModel.signUpScreen.confirmPasswordData,
                topPadding = defaultPadding,
                textDecoration = TextDecoration.None,
                visualTransformation = PasswordVisualTransformation()
            )
            DatePickerView(
                dateState = ViewModel.signUpScreen.dateData,
                mDatePickerDialog = createDatePicker(data = ViewModel.signUpScreen.dateData.dateData),
                topPadding = defaultPadding
            )
            SexPicker(
                maleSexData = ViewModel.signUpScreen.maleSexData,
                femaleSexData = ViewModel.signUpScreen.femaleSexData,
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
                        areFilledFields = ViewModel.signUpScreen.areFilledFields,
                        paddingValues = defaultTopPadding
                    ) {
                        CoroutineScope(Dispatchers.Main).launch {
                            ViewModel.signUpScreen.onClickRegister(context)
                        }
                        Log.i("tokenValue", Repositories.authRepository.getUserToken(context).token)
                        navigator.popBackStack(SignUpScreenDestination, true)
                        navigator.navigate(MainScreenDestination)
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
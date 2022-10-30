package com.example.myapplication.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.domain.createDatePicker
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.*

@Preview
@Composable
fun SignUpScreen() {

    val mDate = remember { mutableStateOf("") }
    val mDatePickerDialog = createDatePicker(data = mDate)

    val loginData = remember { mutableStateOf("") }
    val emailData = remember { mutableStateOf("") }
    val nameData = remember { mutableStateOf("") }
    val passwordData = remember { mutableStateOf("") }
    val confirmPasswordData = remember { mutableStateOf("") }
    val maleSexData = remember { mutableStateOf(false) }
    val femaleSexData = remember { mutableStateOf(false) }

    val areFilledFields =
        loginData.value.isNotEmpty() && emailData.value.isNotEmpty() && nameData.value.isNotEmpty() && passwordData.value.isNotEmpty() && confirmPasswordData.value.isNotEmpty() && mDate.value.isNotEmpty() && (maleSexData.value || femaleSexData.value)
    Surface(modifier = Modifier.fillMaxSize(), color = backgroundColor) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sign_up_screen_logo),
                contentDescription = "Sign-up screen app logo and text",
                modifier = Modifier.padding(top = 32.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(state = rememberScrollState())
                    .padding(horizontal = defaultPadding)
            ) {
                SectionText(text = "Регистрация", paddingValues = defaultTopPadding)
                OutlinedTextFieldView(
                    placeholderText = "Логин",
                    data = loginData,
                    topPadding = defaultPadding,
                    textDecoration = TextDecoration.None
                )
                OutlinedTextFieldView(
                    placeholderText = "E-mail",
                    data = emailData,
                    topPadding = defaultPadding,
                    textDecoration = TextDecoration.None
                )
                OutlinedTextFieldView(
                    placeholderText = "Имя",
                    data = nameData,
                    topPadding = defaultPadding,
                    textDecoration = TextDecoration.None
                )
                OutlinedPasswordFieldView(
                    placeholderText = "Пароль",
                    data = passwordData
                )
                OutlinedPasswordFieldView(
                    placeholderText = "Подтвердите пароль",
                    data = confirmPasswordData
                )
                DatePickerView(mDate = mDate, mDatePickerDialog = mDatePickerDialog, topPadding = defaultPadding)
                SexPicker(maleSexData = maleSexData, femaleSexData = femaleSexData, topPadding = defaultPadding)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = defaultPadding),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column {
                        OutlinedButtonView(
                            buttonText = "Зарегистрироваться",
                            areFilledFields = areFilledFields,
                            paddingValues = defaultTopPadding
                        )
                        ButtonView(
                            buttonText = "У меня уже есть аккаунт",
                            paddingValues = commonButtonPaddings,
                            backgroundColor = backgroundColor,
                            textColor = textColor
                        )
                    }
                }
            }
        }
    }
}
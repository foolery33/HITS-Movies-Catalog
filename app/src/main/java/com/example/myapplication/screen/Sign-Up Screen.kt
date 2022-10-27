package com.example.myapplication.screen

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.domain.createDatePicker
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.*
import java.util.*

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
                Text(
                    "Регистрация",
                    color = textColor,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = defaultPadding),
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700)
                )
                OutlinedTextFieldView(placeholderText = "Логин", data = loginData)
                OutlinedTextFieldView(placeholderText = "E-mail", data = emailData)
                OutlinedTextFieldView(placeholderText = "Имя", data = nameData)
                OutlinedPasswordFieldView(
                    placeholderText = "Пароль",
                    data = passwordData
                )
                OutlinedPasswordFieldView(
                    placeholderText = "Подтвердите пароль",
                    data = confirmPasswordData
                )
                DatePickerView(mDate = mDate, mDatePickerDialog = mDatePickerDialog)
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = defaultPadding)
                ) {
                    OutlinedButton(
                        onClick = {
                            maleSexData.value = !maleSexData.value
                            if (femaleSexData.value) {
                                femaleSexData.value = !femaleSexData.value
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        content = {
                            Text(
                                "Мужчина",
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }, colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = if (maleSexData.value) logInButtonColor else backgroundColor,
                            contentColor = if (maleSexData.value) baseWhite else placeholderTextColor
                        ),
                        border = BorderStroke(1.dp, strokeColor),
                        shape = sexButtonMale
                    )
                    OutlinedButton(
                        onClick = {
                            femaleSexData.value = !femaleSexData.value
                            if (maleSexData.value) {
                                maleSexData.value = !maleSexData.value
                            }
                        },
                        modifier = Modifier.fillMaxWidth(1f),
                        content = {
                            Text(
                                "Женщина",
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }, colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = if (femaleSexData.value) logInButtonColor else backgroundColor,
                            contentColor = if (femaleSexData.value) baseWhite else placeholderTextColor
                        ),
                        border = BorderStroke(1.dp, strokeColor),
                        shape = sexButtonFemale
                    )

                }
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
                            paddingValues = firstButtonPaddings
                        )
                        ButtonView(
                            buttonText = "У меня уже есть аккаунт",
                            paddingValues = commonButtonPaddings
                        )
                    }
                }
            }
        }
    }
}
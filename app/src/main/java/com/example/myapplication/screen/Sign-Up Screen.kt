package com.example.myapplication.screen

import android.app.DatePickerDialog
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
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.OutlinedTextFieldView
import com.example.myapplication.view.OutlinedPasswordFieldView
import java.util.*

@Preview
@Composable
fun SignUpScreen() {
    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )

    val loginData = remember { mutableStateOf("") }
    val emailData = remember { mutableStateOf("") }
    val nameData = remember { mutableStateOf("") }
    val passwordData = remember { mutableStateOf("") }
    val confirmPasswordData = remember { mutableStateOf("") }
    val birthDateData = remember { mutableStateOf("") }
    var maleSexData = remember { mutableStateOf(false) }
    val femaleSexData = remember { mutableStateOf(false) }
    Surface(modifier = Modifier.fillMaxSize(), color = backgroundColor) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(ScrollState(0)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sign_up_screen_logo),
                contentDescription = "Sign-up screen app logo and text",
                modifier = Modifier.padding(top = 32.dp)
            )
            Text(
                "Регистрация",
                color = textColor,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight(700)
            )
            OutlinedTextFieldView(placeholderText = "Логин", data = loginData)
            OutlinedTextFieldView(placeholderText = "E-mail", data = emailData)
            OutlinedTextFieldView(placeholderText = "Имя", data = nameData)
            OutlinedPasswordFieldView(placeholderText = "Пароль", data = passwordData)
            OutlinedPasswordFieldView(
                placeholderText = "Подтвердите пароль",
                data = confirmPasswordData
            )
            OutlinedTextField(
                enabled = false,
                value = mDate.value.replace('/', '.'),
                onValueChange = { birthDateData.value = mDate.value },
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .clickable(indication = null, interactionSource = remember {
                        MutableInteractionSource()
                    }) { mDatePickerDialog.show() },
                singleLine = true,
                placeholder = { Text(text = "Дата рождения", fontWeight = FontWeight(400)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = strokeColor,
                    unfocusedBorderColor = strokeColor,
                    disabledBorderColor = strokeColor,
                    disabledPlaceholderColor = placeholderTextColor,
                    backgroundColor = backgroundColor,
                    placeholderColor = placeholderTextColor,
                    disabledTextColor = textColor
                ),
                textStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight(400)),
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "calendar",
                        modifier = Modifier.padding(end = 18.dp),
                        tint = strokeColor
                    )
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
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
                        backgroundColor = if(maleSexData.value) logInButtonColor else backgroundColor,
                        contentColor = if(maleSexData.value) baseWhite else placeholderTextColor
                    ),
                    border = BorderStroke(1.dp, strokeColor),
                    shape = RoundedCornerShape(
                        topEnd = 0.dp,
                        topStart = 8.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 8.dp
                    )
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
                        backgroundColor = if(femaleSexData.value) logInButtonColor else backgroundColor,
                        contentColor = if(femaleSexData.value) baseWhite else placeholderTextColor
                    ),
                    border = BorderStroke(1.dp, strokeColor),
                    shape = RoundedCornerShape(
                        topEnd = 8.dp,
                        topStart = 0.dp,
                        bottomEnd = 8.dp,
                        bottomStart = 0.dp
                    )
                )
            }
        }
    }
}
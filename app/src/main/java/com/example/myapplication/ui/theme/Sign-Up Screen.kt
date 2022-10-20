package com.example.myapplication.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Preview
@Composable
fun SingUpScreen() {
    val loginData = remember { mutableStateOf("") }
    val emailData = remember { mutableStateOf("") }
    val nameData = remember { mutableStateOf("") }
    val passwordData = remember { mutableStateOf("") }
    val confirmPassordData = remember { mutableStateOf("") }
    val birthDateData = remember { mutableStateOf("") }
    val sexData = remember { mutableStateOf(null) }
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
            Text(
                "Регистрация",
                color = textColor,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight(700)
            )
            OutlinedTextField(
                value = loginData.value,
                onValueChange = { loginData.value = it },
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Логин", fontWeight = FontWeight(400)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = strokeColor,
                    unfocusedBorderColor = strokeColor,
                    textColor = textColor,
                    backgroundColor = backgroundColor,
                    placeholderColor = placeholderTextColor
                ),
                textStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight(400)),
                shape = RoundedCornerShape(8.dp)
            )
            OutlinedTextField(
                value = emailData.value,
                onValueChange = { emailData.value = it },
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("E-mail", fontWeight = FontWeight(400)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = strokeColor,
                    unfocusedBorderColor = strokeColor,
                    textColor = textColor,
                    backgroundColor = backgroundColor,
                    placeholderColor = placeholderTextColor
                ),
                textStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight(400)),
                shape = RoundedCornerShape(8.dp)
            )
        }
    }
}
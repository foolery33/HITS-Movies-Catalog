package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myapplication.screen.MainScreen
import com.example.myapplication.screen.ProfileScreen
import com.example.myapplication.screen.SignUpScreen
import com.example.myapplication.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            ProfileScreen()
        }
    }
}

@Preview
@Composable
fun SignInScreen() {
    val loginData = remember { mutableStateOf("") }
    val passwordData = remember { mutableStateOf("") }
    Surface(modifier = Modifier.fillMaxSize(), color = backgroundColor) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.text_and_logo),
                contentDescription = "App logo and name",
                modifier = Modifier.padding(top = 32.dp)
            )
            OutlinedTextField(
                value = loginData.value,
                onValueChange = { loginData.value = it },
                modifier = Modifier
                    .padding(top = 48.dp, start = 16.dp, end = 16.dp)
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
                value = passwordData.value,
                onValueChange = { passwordData.value = it },
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Пароль", fontWeight = FontWeight(400)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = strokeColor,
                    unfocusedBorderColor = strokeColor,
                    textColor = textColor,
                    backgroundColor = backgroundColor,
                    placeholderColor = placeholderTextColor
                ),
                textStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight(400)),
                shape = RoundedCornerShape(8.dp),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column {
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 164.dp),
                        content = { Text("Войти", fontSize = 16.sp, fontWeight = FontWeight(500)) },
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = if (loginData.value.isNotEmpty() && passwordData.value.isNotEmpty()) logInButtonColor else backgroundColor,
                            contentColor = if (loginData.value.isNotEmpty() && passwordData.value.isNotEmpty()) Color.White else textColor
                        ),
                        border = if (loginData.value.isNotEmpty() && passwordData.value.isNotEmpty()) BorderStroke(
                            0.dp,
                            logInButtonColor
                        ) else BorderStroke(1.dp, strokeColor),
                        shape = RoundedCornerShape(4.dp),
                        contentPadding = buttonTextPaddings
                    )
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                        content = {
                            Text(
                                "Регистрация",
                                fontSize = 16.sp,
                                fontWeight = FontWeight(500)
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = backgroundColor,
                            contentColor = textColor
                        ),
                    )
                }
            }
        }
    }
}

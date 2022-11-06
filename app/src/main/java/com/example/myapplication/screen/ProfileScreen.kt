package com.example.myapplication.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.domain.createDatePicker
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.*
import com.example.myapplication.viewmodel.profile_screen.rememberProfileScreenState
import com.example.myapplication.viewmodel.sign_up_screen.rememberDatePickerState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun ProfileScreen() {

    val profileScreenState = rememberProfileScreenState()

    Scaffold(bottomBar = { BottomBar() }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .verticalScroll(state = rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .padding(startTopEndDefaultPadding)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(88.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "Тест", fontSize = sectionTextSize, fontWeight = FontWeight(
                        sectionButtonFontWeight
                    ), color = Color.White,
                    modifier = Modifier.padding(start = defaultPadding)
                )
            }
            Box(modifier = Modifier.padding(startTopEndDefaultPadding)) {
                Column {
                    Text(
                        text = "E-mail",
                        fontSize = buttonTextSize,
                        fontWeight = FontWeight(buttonFontWeight),
                        color = strokeColor
                    )
                    OutlinedTextFieldView(
                        placeholderText = "",
                        data = profileScreenState.emailData,
                        topPadding = halfDefaultPadding,
                        textDecoration = TextDecoration.None,
                        visualTransformation = VisualTransformation.None
                    )
                }
            }
            Box(modifier = Modifier.padding(startTopEndDefaultPadding)) {
                Column {
                    Text(
                        text = "Ссылка на аватарку",
                        fontSize = buttonTextSize,
                        fontWeight = FontWeight(buttonFontWeight),
                        color = strokeColor
                    )
                    OutlinedTextFieldView(
                        placeholderText = "",
                        data = profileScreenState.linkData,
                        topPadding = halfDefaultPadding,
                        textDecoration = TextDecoration.Underline,
                        visualTransformation = VisualTransformation.None
                    )
                }
            }
            Box(modifier = Modifier.padding(startTopEndDefaultPadding)) {
                Column {
                    Text(
                        text = "Имя",
                        fontSize = buttonTextSize,
                        fontWeight = FontWeight(buttonFontWeight),
                        color = strokeColor
                    )
                    OutlinedTextFieldView(
                        placeholderText = "",
                        data = profileScreenState.nameData,
                        topPadding = halfDefaultPadding,
                        textDecoration = TextDecoration.None,
                        visualTransformation = VisualTransformation.None
                    )
                }
            }
            Box(modifier = Modifier.padding(startTopEndDefaultPadding)) {
                Column {
                    Text(
                        text = "Дата рождения",
                        fontSize = buttonTextSize,
                        fontWeight = FontWeight(buttonFontWeight),
                        color = strokeColor
                    )
                    DatePickerView(
                        profileScreenState.dateData,
                        mDatePickerDialog = createDatePicker(data = profileScreenState.dateData.dateData),
                        topPadding = halfDefaultPadding
                    )
                }
            }
            Box(modifier = Modifier.padding(startTopEndDefaultPadding)) {
                Column {
                    Text(
                        text = "Пол",
                        fontSize = buttonTextSize,
                        fontWeight = FontWeight(buttonFontWeight),
                        color = strokeColor
                    )
                    SexPicker(
                        maleSexData = profileScreenState.maleSexData,
                        femaleSexData = profileScreenState.femaleSexData,
                        topPadding = halfDefaultPadding
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(startBottomEndDefaultPadding),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column {
                    OutlinedButtonView(
                        buttonText = "Сохранить",
                        areFilledFields = profileScreenState.areFilledFields,
                        paddingValues = doubleDefaultTopPadding
                    )
                    ButtonView(
                        buttonText = "Выйти из аккаунта",
                        paddingValues = halfDefaultTopPadding,
                        backgroundColor = backgroundColor,
                        textColor = textColor
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}
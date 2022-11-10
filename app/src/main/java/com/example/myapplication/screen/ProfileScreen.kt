package com.example.myapplication.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.domain.ViewModel
import com.example.myapplication.domain.createDatePicker
import com.example.myapplication.screen.destinations.SignInScreenDestination
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.*
import com.example.myapplication.viewmodel.profile_screen.ProfileScreenState
import com.example.myapplication.viewmodel.profile_screen.rememberProfileScreenState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun ProfileScreen(navigator: DestinationsNavigator) {

    Scaffold(bottomBar = { BottomBar(navigator, 1) }) {
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
                        data = ViewModel.profileScreen.emailData,
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
                        data = ViewModel.profileScreen.linkData,
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
                        data = ViewModel.profileScreen.nameData,
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
                        ViewModel.profileScreen.dateData,
                        mDatePickerDialog = createDatePicker(data = ViewModel.profileScreen.dateData.dateData),
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
                        maleSexData = ViewModel.profileScreen.maleSexData,
                        femaleSexData = ViewModel.profileScreen.femaleSexData,
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
                        areFilledFields = ViewModel.profileScreen.areFilledFields,
                        paddingValues = doubleDefaultTopPadding
                    ) {

                    }
                    ButtonView(
                        buttonText = "Выйти из аккаунта",
                        paddingValues = halfDefaultTopPadding,
                        backgroundColor = backgroundColor,
                        textColor = textColor,
                        contentPadding = PaddingValues(6.dp)
                    ) {
                        navigator.navigate(SignInScreenDestination)
                    }
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}
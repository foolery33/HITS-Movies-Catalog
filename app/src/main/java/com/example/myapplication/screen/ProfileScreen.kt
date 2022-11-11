package com.example.myapplication.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.myapplication.R
import com.example.myapplication.domain.ViewModel
import com.example.myapplication.domain.createDatePicker
import com.example.myapplication.screen.destinations.SignInScreenDestination
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun ProfileScreen(navigator: DestinationsNavigator) {

    val context = LocalContext.current

    Scaffold(bottomBar = { BottomBar(navigator, 1) }) {
        Log.i("avatarLink", ViewModel.profileScreen.showPictureByLink.value)
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
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .clip(shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .size(88.dp)
                            .clip(shape = CircleShape),
                        contentScale = ContentScale.FillHeight,
                        model = ViewModel.profileScreen.showPictureByLink.value,
                        loading = { CircularProgressIndicator() },
                        contentDescription = "",
                        error = { Image(painterResource(R.drawable.pfp_anonym), "") })
                }
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
                        CoroutineScope(Dispatchers.Main).launch {
                            ViewModel.profileScreen.onClickSave(context = context)
                        }
                    }
                    ButtonView(
                        buttonText = "Выйти из аккаунта",
                        paddingValues = halfDefaultTopPadding,
                        backgroundColor = backgroundColor,
                        textColor = textColor,
                        contentPadding = PaddingValues(6.dp)
                    ) {
                        CoroutineScope(Dispatchers.Main).launch {
                            ViewModel.profileScreen.onClickLogout(context, navigator)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}
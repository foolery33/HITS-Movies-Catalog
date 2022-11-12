@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.myapplication.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.R
import com.example.myapplication.domain.ViewModels
import com.example.myapplication.domain.general_use_cases.MakeToastUseCase
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.ButtonView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun ReviewDialog(context: Context, movieId: String, id: String) {
    Log.i("reviewDialogState", "I'm on")

    if (ViewModels.reviewDialog.showDialog.value) {
        Dialog(
            onDismissRequest = {
                ViewModels.reviewDialog.showDialog.value = false
                ViewModels.reviewDialog.textData.value = ""
                ViewModels.reviewDialog.checkedState.value = false
                ViewModels.reviewDialog.ratingValue.value = 0
            },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = defaultPadding),
                color = reviewDialogColor,
                shape = RoundedCornerShape(
                    defaultPadding
                )
            ) {
                Column(modifier = Modifier.padding(defaultPadding)) {
                    Text(
                        "Оставить отзыв",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = sectionTextSize
                    )
                    Box {
                        Column(verticalArrangement = Arrangement.spacedBy(defaultPadding)) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = defaultPadding)
                                    .height(24.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Star(1, ViewModels.reviewDialog.ratingValue)
                                    Star(2, ViewModels.reviewDialog.ratingValue)
                                    Star(3, ViewModels.reviewDialog.ratingValue)
                                    Star(4, ViewModels.reviewDialog.ratingValue)
                                    Star(5, ViewModels.reviewDialog.ratingValue)
                                    Star(6, ViewModels.reviewDialog.ratingValue)
                                    Star(7, ViewModels.reviewDialog.ratingValue)
                                    Star(8, ViewModels.reviewDialog.ratingValue)
                                    Star(9, ViewModels.reviewDialog.ratingValue)
                                    Star(10, ViewModels.reviewDialog.ratingValue)
                                }
                            }
                            TextField(value = ViewModels.reviewDialog.textData.value,
                                onValueChange = { ViewModels.reviewDialog.textData.value = it },
                                modifier = Modifier
                                    .height(120.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(6.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    placeholderColor = reviewPlaceholder,
                                    textColor = backgroundColor,
                                    backgroundColor = Color.White
                                ),
                                textStyle = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight.Medium,
                                    color = backgroundColor,
                                    fontSize = movieInfoFontSize,
                                ),
                                placeholder = {
                                    Text(
                                        text = "Отзыв",
                                        fontSize = movieInfoFontSize,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = FontFamily(Font(R.font.montserrat))
                                    )
                                }
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Анонимный отзыв",
                                    color = Color.White,
                                    fontSize = buttonTextSize,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .border(
                                                width = 1.dp,
                                                color = placeholderTextColor,
                                                shape = RoundedCornerShape(4.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Checkbox(
                                            checked = ViewModels.reviewDialog.checkedState.value,
                                            onCheckedChange = { ViewModels.reviewDialog.checkedState.value = it },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = Color.Transparent,
                                                uncheckedColor = Color.Transparent,
                                                checkmarkColor = textColor
                                            )
                                        )
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                                    ButtonView(
                                        buttonText = "Сохранить",
                                        paddingValues = PaddingValues(0.dp),
                                        backgroundColor = logInButtonColor,
                                        textColor = Color.White,
                                        contentPadding = PaddingValues(16.dp)
                                    ) {
                                        if(ViewModels.reviewDialog.textData.value != "") {
                                            CoroutineScope(Dispatchers.Main).launch {
                                                ViewModels.reviewDialog.onClickSave(context = context,
                                                    movieId = movieId,
                                                    id = id
                                                )
                                            }
                                        }
                                        else {
                                            MakeToastUseCase().show(context, "Поле отзыва должно быть обязательно заполнено")
                                        }
                                    }
                                    ButtonView(
                                        buttonText = "Отмена",
                                        paddingValues = PaddingValues(0.dp),
                                        backgroundColor = reviewDialogColor,
                                        textColor = textColor,
                                        contentPadding = PaddingValues(6.dp)
                                    ) {
                                        ViewModels.reviewDialog.showDialog.value = false
                                        ViewModels.reviewDialog.textData.value = ""
                                        ViewModels.reviewDialog.checkedState.value = false
                                        ViewModels.reviewDialog.ratingValue.value = 0
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Star(id: Int, ratingValue: MutableState<Int>) {
    Box(modifier = Modifier
        .size(24.dp)
        .clip(CircleShape)
        .background(if (ratingValue.value >= id) textColorOpacity else Color.Transparent), contentAlignment = Alignment.Center) {
        IconButton(
            onClick = { ratingValue.value = id },
            modifier = Modifier.size(18.dp)
        ) {
            Icon(
                painter = if(ratingValue.value >= id) painterResource(id = R.drawable.pressed_star) else painterResource(
                    id = R.drawable.star
                ),
                contentDescription = "",
                tint = if(ratingValue.value >= id) textColor else placeholderTextColor
            )
        }
    }
}

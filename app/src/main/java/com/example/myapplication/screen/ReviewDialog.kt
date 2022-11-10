@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.myapplication.screen

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.R
import com.example.myapplication.screen.destinations.Destination
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.ButtonView
import com.example.myapplication.viewmodel.movie_screen.ReviewDialogState
import com.example.myapplication.viewmodel.movie_screen.rememberReviewDialogState

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun ReviewDialog(reviewDialogState: ReviewDialogState) {
    Log.i("reviewDialogState", "I'm on")

    if (reviewDialogState.showDialog.value) {
        Dialog(
            onDismissRequest = {
                reviewDialogState.showDialog.value = false
                reviewDialogState.textData.value = ""
                reviewDialogState.checkedState.value = false
                reviewDialogState.ratingValue.value = 0
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
                                    Star(1, reviewDialogState.ratingValue)
                                    Star(2, reviewDialogState.ratingValue)
                                    Star(3, reviewDialogState.ratingValue)
                                    Star(4, reviewDialogState.ratingValue)
                                    Star(5, reviewDialogState.ratingValue)
                                    Star(6, reviewDialogState.ratingValue)
                                    Star(7, reviewDialogState.ratingValue)
                                    Star(8, reviewDialogState.ratingValue)
                                    Star(9, reviewDialogState.ratingValue)
                                    Star(10, reviewDialogState.ratingValue)
                                }
                            }
                            TextField(value = reviewDialogState.textData.value,
                                onValueChange = { reviewDialogState.textData.value = it },
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
                                            checked = reviewDialogState.checkedState.value,
                                            onCheckedChange = { reviewDialogState.checkedState.value = it },
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
                                        reviewDialogState.showDialog.value = false
                                        reviewDialogState.textData.value = ""
                                        reviewDialogState.checkedState.value = false
                                        reviewDialogState.ratingValue.value = 0
                                    }
                                    ButtonView(
                                        buttonText = "Отмена",
                                        paddingValues = PaddingValues(0.dp),
                                        backgroundColor = reviewDialogColor,
                                        textColor = textColor,
                                        contentPadding = PaddingValues(6.dp)
                                    ) {
                                        reviewDialogState.showDialog.value = false
                                        reviewDialogState.textData.value = ""
                                        reviewDialogState.checkedState.value = false
                                        reviewDialogState.ratingValue.value = 0
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

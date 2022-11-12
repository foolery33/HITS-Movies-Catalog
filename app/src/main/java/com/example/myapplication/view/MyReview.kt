@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.myapplication.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.domain.ViewModels
import com.example.myapplication.domain.profile_screen.DateReverseConverter
import com.example.myapplication.network.movie.ReviewModel
import com.example.myapplication.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyReview(context: Context, review: ReviewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(3.dp, textColor, RoundedCornerShape(8.dp))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            ) {
                Row(modifier = Modifier.align(Alignment.TopStart)) {
                    AsyncImage(
                        model = ViewModels.profileScreen.linkData,
                        contentDescription = "",
                        modifier = Modifier
                            .clip(
                                CircleShape
                            )
                            .size(40.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = ViewModels.profileScreen.nickName.value,
                            color = Color.White,
                            fontSize = buttonTextSize,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            "мой отзыв",
                            color = strokeColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Text(
                        text = review.rating.toString(),
                        modifier = Modifier
                            .background(
                                color = textColor,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        color = Color.White,
                        fontSize = buttonTextSize,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Box(modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)) {
                Text(
                    text = review.reviewText ?: "",
                    color = Color.White,
                    fontSize = movieInfoFontSize,
                    fontWeight = FontWeight.Normal
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 4.dp, bottom = 8.dp, end = 8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = DateReverseConverter().toProfileFormat(review.createDateTime),
                        color = strokeColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        IconButton(
                            onClick = {
                                ViewModels.reviewDialog.action = "edit"
                                ViewModels.reviewDialog.showDialog.value = true

                            }, modifier = Modifier
                                .size(24.dp)
                                .background(
                                    color = Color(0x40BDBDBD),
                                    shape = RoundedCornerShape(24.dp)
                                )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.edit_review),
                                contentDescription = "",
                                tint = placeholderTextColor
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        IconButton(
                            onClick = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    ViewModels.reviewDialog.onClickDelete(
                                        context,
                                        review.id,
                                        ViewModels.profileScreen.id.value
                                    )
                                }
                            }, modifier = Modifier
                                .size(24.dp)
                                .background(
                                    color = Color(0x40EF3A01),
                                    shape = RoundedCornerShape(24.dp)
                                )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete_review),
                                contentDescription = "",
                                tint = placeholderTextColor
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}
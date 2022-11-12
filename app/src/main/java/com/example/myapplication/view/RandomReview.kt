package com.example.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.example.myapplication.domain.profile_screen.DateReverseConverter
import com.example.myapplication.network.movie.ReviewModel
import com.example.myapplication.ui.theme.*

@Composable
fun RandomReview(review: ReviewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, placeholderTextColor, RoundedCornerShape(8.dp))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            ) {
                Row(modifier = Modifier.align(Alignment.TopStart)) {
                    if (review.isAnonymous || review.author == null) {
                        Image(
                            painterResource(id = R.drawable.pfp_anonym),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(
                                    CircleShape
                                )
                                .size(40.dp),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        if (review.author.avatar == null) {
                            Image(
                                painterResource(id = R.drawable.pfp_anonym),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(
                                        CircleShape
                                    )
                                    .size(40.dp),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            AsyncImage(
                                model = review.author.avatar,
                                contentDescription = "",
                                modifier = Modifier
                                    .clip(
                                        CircleShape
                                    )
                                    .size(40.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (review.author != null) {
                                review.author.nickName ?: "Анонимный пользователь"
                            } else {
                                "Анонимный пользователь"
                            },
                            color = Color.White,
                            fontSize = buttonTextSize,
                            fontWeight = FontWeight.Medium
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
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}
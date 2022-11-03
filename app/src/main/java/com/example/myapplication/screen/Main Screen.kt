package com.example.myapplication.screen

import android.annotation.SuppressLint
import android.widget.ImageButton
import android.widget.Space
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.BottomBar
import com.example.myapplication.view.ButtonView
import com.example.myapplication.view.SectionText

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun MainScreen() {

    var sizeImage by remember { mutableStateOf(IntSize.Zero) }

    Scaffold(bottomBar = { BottomBar() }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(halfDefaultPadding)
        ) {
            item {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.magicians_poster),
                        contentDescription = "",
                        modifier = Modifier
                            .onGloballyPositioned { sizeImage = it.size }
                            .fillMaxWidth(), contentScale = ContentScale.Crop)
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent, Color.Black
                                    ),
                                    startY = sizeImage.height.toFloat() / 1.4f,
                                    endY = sizeImage.height.toFloat()
                                )
                            )
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(bottom = 32.dp), contentAlignment = Alignment.BottomCenter
                    ) {
                        ButtonView(
                            buttonText = "Смотреть",
                            paddingValues = PaddingValues(horizontal = 110.dp),
                            backgroundColor = logInButtonColor,
                            textColor = Color.White
                        )
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxSize()
                ) {
                    Favourites()
                    SectionText(text = "Галерея", paddingValues = doubleDefaultTopPadding)
                }
            }
            items(5) {
                GalleryElement(
                    name = "Люцифер",
                    year = 1999,
                    country = "США",
                    genres = "драма, криминал",
                    rating = 9
                )
            }
            item {
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}

@Composable
fun Favourites() {
    SectionText(text = "Избранное", paddingValues = mainScreenLazyPadding)
    LazyRow(
        modifier = Modifier.padding(top = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(defaultPadding)
    ) {
        items(5) {
            // TODO: Перенести айтем в отдельный файл
            Box(
                modifier = Modifier
                    .height(144.dp)
                    .width(100.dp)
            ) {
                IconButton(onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.movie_img),
                        contentDescription = "", modifier = Modifier
                            .height(144.dp)
                            .width(100.dp), contentScale = ContentScale.Fit
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(12.dp)
                        .padding(top = 4.dp, end = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.delete_favourite),
                        contentDescription = "",
                    )
                }
            }
        }
    }
}

@Composable
fun GalleryElement(
    name: String,
    year: Int,
    country: String,
    genres: String,
    rating: Int
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(144.dp)
                .width(100.dp)
        ) {
            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(id = R.drawable.movie_img),
                    contentDescription = "", modifier = Modifier
                        .height(144.dp)
                        .width(100.dp), contentScale = ContentScale.Fit
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Column(modifier = Modifier.fillMaxHeight()) {
                // Название фильма
                Text(
                    text = name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight(
                        sectionButtonFontWeight
                    )
                )
                // Год и жанр фильма
                Text(
                    text = "$year • $country",
                    color = Color.White,
                    fontSize = movieInfoFontSize,
                    fontWeight = FontWeight(defaultFontWeight),
                    modifier = Modifier.padding(top = 4.dp)
                )
                // Жанры
                Text(
                    text = genres,
                    color = Color.White,
                    fontSize = movieInfoFontSize,
                    fontWeight = FontWeight(defaultFontWeight),
                    modifier = Modifier.padding(top = 4.dp)
                )
                Spacer(modifier = Modifier.height(43.dp))
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(56.dp, 28.dp),
                    colors = ButtonDefaults.buttonColors(
                        disabledBackgroundColor = Color.Green
                    ),
                    enabled = false,
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues(bottom = 2.dp),
                    content = {
                        Text(
                            text = "9",
                            color = Color.White,
                            fontSize = buttonTextSize,
                            fontWeight = FontWeight(buttonFontWeight)
                        )
                    }
                )
            }
        }
    }
}
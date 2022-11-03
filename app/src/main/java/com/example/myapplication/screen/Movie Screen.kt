package com.example.myapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.AboutFilmRow
import com.example.myapplication.view.MovieGenre
import com.example.myapplication.view.MyReview
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import me.onebone.toolbar.*

val headerHeightDp = 275.dp

@Composable
fun MovieScreen() {
    val state = rememberCollapsingToolbarScaffoldState()
    val progress = state.toolbarState.progress // how much the toolbar is expanded (0: collapsed, 1: expanded)
    val headerHeightPx = with(LocalDensity.current) { headerHeightDp.toPx() }

    val enabled by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxWidth()) {
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbarModifier = Modifier.background(backgroundColor),
            enabled = enabled,
            toolbar = {
                // Collapsing toolbar collapses its size as small as the that of
                // a smallest child. To make the toolbar collapse to 50dp, we create
                // a dummy Spacer composable.
                // You may replace it with TopAppBar or other preferred composable.
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.movie_screen_poster),
                    modifier = Modifier
                        .parallax(0.5f)
                        .height(headerHeightDp)
                        .graphicsLayer {
                            // change alpha of Image as the toolbar expands
                            alpha = state.toolbarState.progress
                        },
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(headerHeightDp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0x33000000)),
                                startY = 3 * headerHeightPx / 4,
                                endY = headerHeightPx // Gradient applied to wrap the title only
                            )
                        )
                )
                Text(
                    text = "Побег из Шоушенка",
                    fontSize = (24 + (36 - 24) * progress).sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .road(Alignment.TopStart, Alignment.BottomStart)
                        .padding((49 + (16 - 49) * progress).dp, 12.dp, 29.dp, 16.dp),
                    maxLines = if(state.toolbarState.progress <= 0) 1 else 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
        ) {
            Body()
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /*TODO*/ }, enabled = state.toolbarState.progress <= 0) {
                Icon(
                    painter = painterResource(id = R.drawable.heart_icon),
                    contentDescription = "",
                    tint = if (state.toolbarState.progress <= 0) textColor else Color.Transparent
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Body() {
    LazyColumn(
        modifier = Modifier.padding(horizontal = defaultPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(0.dp))
        }
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения",
                    color = Color.White,
                    fontSize = movieInfoFontSize,
                    fontWeight = FontWeight.Normal
                )
            }
        }
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "О фильме",
                        fontSize = buttonTextSize,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = halfDefaultPadding)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            AboutFilmRow("Год", "1994")
                            AboutFilmRow("Страна", "США")
                            AboutFilmRow("Время", "142 мин.")
                            AboutFilmRow("Слоган", "\"Страх - это кандалы. Надежда - это свобода\"")
                            AboutFilmRow("Режиссёр", "Фрэнк Дарабонт")
                            AboutFilmRow("Бюджет", "$25 000 000")
                            AboutFilmRow("Сборы в мире", "$28 418 687")
                            AboutFilmRow("Возраст", "16+")
                        }
                    }
                }
            }
        }
        item {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Жанры",
                        fontSize = buttonTextSize,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    FlowRow(
                        modifier = Modifier.padding(top = 8.dp),
                        mainAxisAlignment = MainAxisAlignment.Start,
                        mainAxisSize = SizeMode.Expand,
                        crossAxisSpacing = 12.dp,
                        mainAxisSpacing = 8.dp
                    ) {
                        MovieGenre(genre = "драма")
                        MovieGenre(genre = "боевик")
                        MovieGenre(genre = "фантастика")
                        MovieGenre(genre = "мелодрама")
                    }
                }
            }
        }
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text("Отзывы", fontWeight = FontWeight.Medium, fontSize = buttonTextSize, color = Color.White, modifier = Modifier.align(
                    Alignment.TopStart))
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterEnd)) {
                        Icon(painter = painterResource(id = R.drawable.plus_sign), contentDescription = "", tint = textColor)
                    }
                }
            }
        }
        items(5) {
            MyReview()
        }
    }
}
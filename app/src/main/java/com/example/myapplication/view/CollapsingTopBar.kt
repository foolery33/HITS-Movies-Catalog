package com.example.myapplication.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.*
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode

private val headerHeight = 275.dp
private val toolbarHeight = 56.dp

private val paddingMedium = 16.dp

private val titlePaddingStart = 16.dp
private val titlePaddingEnd = 60.dp

private const val titleFontScaleStart = 1f
private const val titleFontScaleEnd = 0.66f

@Composable
fun CollapsingToolbarParallaxEffect() {
    val scroll: ScrollState = rememberScrollState(0)

    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.toPx() }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(scroll, headerHeightPx)
        Body(scroll)
        Toolbar(scroll, headerHeightPx, toolbarHeightPx)
        Title(scroll, headerHeightPx, toolbarHeightPx)
    }
}

@Composable
private fun Header(scroll: ScrollState, headerHeightPx: Float) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .height(headerHeight)
            .graphicsLayer {
                translationY = -scroll.value.toFloat() / 2f // Parallax effect
                alpha = (-1f / headerHeightPx) * scroll.value + 1
            }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black),
                painter = painterResource(id = R.drawable.movie_screen_poster),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0x5C000000)),
                            startY = 3 * headerHeightPx / 4,
                            endY = headerHeightPx // Gradient applied to wrap the title only
                        )
                    )
            )
        }
    }
}

/*@Composable
private fun Body(scroll: ScrollState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scroll)
    ) {
        Spacer(Modifier.height(headerHeight))
        repeat(5) {
            Text(
                text = "something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something something ",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .background(Color(0XFF161616))
                    .padding(16.dp)
            )
        }
    }
}*/

@Composable
private fun Body(scroll: ScrollState) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = defaultPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(headerHeight))
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
                Image(painter = painterResource(id = R.drawable.plus_sign), contentDescription = "", modifier = Modifier.align(Alignment.CenterEnd))
            }
        }
        items(5) {
            MyReview()
        }
    }
}

@Composable
private fun Toolbar(scroll: ScrollState, headerHeightPx: Float, toolbarHeightPx: Float) {
    val toolbarBottom = headerHeightPx - toolbarHeightPx
    val showToolbar by remember {
        derivedStateOf {
            scroll.value >= toolbarBottom
        }
    }

    AnimatedVisibility(
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        TopAppBar(
            modifier = Modifier.background(
                backgroundColor
            ),
            navigationIcon = {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(20.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        tint = Color.White
                    )
                }
            },
            title = {},
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        )
    }
}

@Composable
private fun Title(
    scroll: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float
) {
    var titleHeightPx by remember { mutableStateOf(0f) }
    var titleWidthPx by remember { mutableStateOf(0f) }

    Text(
        text = "Побег из Шоушенка",
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier
            .graphicsLayer {
                val collapseRange: Float = (headerHeightPx - toolbarHeightPx)
                val collapseFraction: Float = (scroll.value / collapseRange).coerceIn(0f, 1f)

                val scaleXY = lerp(
                    titleFontScaleStart.dp,
                    titleFontScaleEnd.dp,
                    collapseFraction
                )

                val titleExtraStartPadding = titleWidthPx.toDp() * (1 - scaleXY.value) / 2f

                val titleYFirstInterpolatedPoint = lerp(
                    headerHeight - titleHeightPx.toDp() - paddingMedium,
                    headerHeight / 2,
                    collapseFraction
                )

                val titleXFirstInterpolatedPoint = lerp(
                    titlePaddingStart,
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    collapseFraction
                )

                val titleYSecondInterpolatedPoint = lerp(
                    headerHeight / 2,
                    toolbarHeight / 2 - titleHeightPx.toDp() / 2,
                    collapseFraction
                )

                val titleXSecondInterpolatedPoint = lerp(
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    titlePaddingEnd - titleExtraStartPadding,
                    collapseFraction
                )

                val titleY = lerp(
                    titleYFirstInterpolatedPoint,
                    titleYSecondInterpolatedPoint,
                    collapseFraction
                )

                val titleX = lerp(
                    titleXFirstInterpolatedPoint,
                    titleXSecondInterpolatedPoint,
                    collapseFraction
                )

                translationY = titleY.toPx()
                translationX = titleX.toPx()
                scaleX = scaleXY.value
                scaleY = scaleXY.value
            }
            .onGloballyPositioned {
                titleHeightPx = it.size.height.toFloat()
                titleWidthPx = it.size.width.toFloat()
            }
    )
}
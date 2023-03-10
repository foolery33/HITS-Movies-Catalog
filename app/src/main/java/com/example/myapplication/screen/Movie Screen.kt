@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.myapplication.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.domain.ViewModels
import com.example.myapplication.domain.main_screen.use_cases.GetMovieDetailsByIdUseCase
import com.example.myapplication.domain.movie_screen.use_cases.CheckIfReviewed
import com.example.myapplication.domain.movie_screen.use_cases.ConvertMoneyViewUseCase
import com.example.myapplication.network.movie.MovieDetailsModel
import com.example.myapplication.screen.destinations.MainScreenDestination
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.AboutFilmRow
import com.example.myapplication.view.MovieGenre
import com.example.myapplication.view.MyReview
import com.example.myapplication.view.RandomReview
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.onebone.toolbar.*

val headerHeightDp = 275.dp

@Destination
@Composable
fun MovieScreen(groupName: String, navigator: DestinationsNavigator) {

    val context = LocalContext.current

    val currentMovie = GetMovieDetailsByIdUseCase().getDetails(groupName)

    val state = rememberCollapsingToolbarScaffoldState()
    val progress =
        state.toolbarState.progress // how much the toolbar is expanded (0: collapsed, 1: expanded)
    val headerHeightPx = with(LocalDensity.current) { headerHeightDp.toPx() }
    val enabled by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Log.i("isFavMovie1", ViewModels.movieScreen.isFavourite.value.toString())
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbarModifier = Modifier.background(backgroundColor),
            enabled = enabled,
            toolbar = {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )
                if (currentMovie.poster != null) {
                    AsyncImage(
                        model = currentMovie.poster,
                        modifier = Modifier
                            .parallax(0.5f)
                            .height(headerHeightDp)
                            .fillMaxWidth()
                            .graphicsLayer {
                                // change alpha of Image as the toolbar expands
                                alpha = state.toolbarState.progress
                            },
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null
                    )
                }
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
                if (currentMovie.name != null) {
                    Text(
                        text = currentMovie.name,
                        fontSize = (24 + (36 - 24) * progress).sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .road(Alignment.TopStart, Alignment.BottomStart)
                            .padding((49 + (16 - 49) * progress).dp, 12.dp, 30.dp, 16.dp),
                        maxLines = if (state.toolbarState.progress <= 0) 1 else 5,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        ) {
            Body(currentMovie)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navigator.navigate(MainScreenDestination) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                if(ViewModels.movieScreen.isFavourite.value) {
                    CoroutineScope(Dispatchers.Main).launch {
                        ViewModels.mainScreen.onClickDelete(context = context, id = currentMovie.id)
                    }
                }
                else {
                    CoroutineScope(Dispatchers.Main).launch {
                        ViewModels.movieScreen.onClickAdd(context = context, id = currentMovie.id)
                    }
                }

            }, enabled = state.toolbarState.progress <= 0) {
                Icon(
                    painter = if (!ViewModels.movieScreen.isFavourite.value) painterResource(id = R.drawable.heart_icon) else painterResource(
                        id = R.drawable.heart_icon_filled
                    ),
                    contentDescription = "",
                    tint = if (state.toolbarState.progress <= 0) textColor else Color.Transparent
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Body(currentMovie: MovieDetailsModel) {
    val context = LocalContext.current
    ReviewDialog(context = context, movieId = currentMovie.id, id = ViewModels.profileScreen.id.value)
    LazyColumn(
        modifier = Modifier.padding(horizontal = defaultPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(0.dp))
        }
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                if (currentMovie.description != null) {
                    Text(
                        text = currentMovie.description,
                        color = Color.White,
                        fontSize = movieInfoFontSize,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "?? ????????????",
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
                            AboutFilmRow("??????", currentMovie.year.toString())
                            if (currentMovie.country != null) {
                                AboutFilmRow("????????????", currentMovie.country)
                            }
                            AboutFilmRow("??????????", "${currentMovie.time} ??????.")
                            if (currentMovie.tagline != null) {
                                AboutFilmRow("????????????", "\"${currentMovie.tagline}\"")
                            }
                            if (currentMovie.director != null) {
                                AboutFilmRow("????????????????", currentMovie.director)
                            }
                            if (currentMovie.budget != null) {
                                AboutFilmRow(
                                    "????????????",
                                    "$${ConvertMoneyViewUseCase().convert(currentMovie.budget.toString())}"
                                )
                            }
                            if (currentMovie.fees != null) {
                                AboutFilmRow(
                                    "?????????? ?? ????????",
                                    "$${ConvertMoneyViewUseCase().convert(currentMovie.fees.toString())}"
                                )
                            }
                            AboutFilmRow("??????????????", "${currentMovie.ageLimit}+")
                        }
                    }
                }
            }
        }
        if (currentMovie.genres != null) {
            if (currentMovie.genres.isNotEmpty()) {
                item {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = "??????????",
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
                                for (genre in currentMovie.genres) {
                                    if (genre.name != null) {
                                        MovieGenre(genre = genre.name)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Log.i("reviewDialogState", ViewModels.reviewDialog.showDialog.value.toString())
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "????????????",
                    fontWeight = FontWeight.Medium,
                    fontSize = buttonTextSize,
                    color = Color.White,
                    modifier = Modifier.align(
                        Alignment.TopStart
                    )
                )
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    IconButton(onClick = {
                        ViewModels.reviewDialog.action = "add"
                        ViewModels.reviewDialog.showDialog.value = true
                    }, modifier = Modifier.align(Alignment.CenterEnd)) {
                        if(!CheckIfReviewed().result(ViewModels.profileScreen.nickName.value, ViewModels.movieScreen.reviewList.value!!)) {
                            Icon(
                                painter = painterResource(id = R.drawable.plus_sign),
                                contentDescription = "",
                                tint = textColor
                            )
                        }
                    }
                }
            }
        }
        if(ViewModels.movieScreen.reviewList.value != null) {
            items(ViewModels.movieScreen.reviewList.value!!) { item ->
                Log.i("NicknameValue1", item.author!!.nickName!!)
                Log.i("NicknameValue2", ViewModels.profileScreen.nickName.value)
                if(item.author.nickName!! == ViewModels.profileScreen.nickName.value)
                    MyReview(context = context, item)
                else {
                    RandomReview(review = item)
                }
            }   
        }
    }
}
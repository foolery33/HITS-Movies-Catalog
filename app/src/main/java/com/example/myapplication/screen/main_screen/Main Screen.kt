package com.example.myapplication.screen.main_screen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.domain.ViewModels
import com.example.myapplication.domain.main_screen.use_cases.ConvertMovieListUseCase
import com.example.myapplication.domain.main_screen.use_cases.MakeColorRatingUseCase
import com.example.myapplication.domain.movie_screen.use_cases.GetMovieRatingUseCase
import com.example.myapplication.network.favourite_movies.MovieModel
import com.example.myapplication.network.movie.MovieElementModel
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.BottomBar
import com.example.myapplication.view.ButtonView
import com.example.myapplication.view.SectionText
import com.example.myapplication.viewmodel.main_screen.MainScreenState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun MainScreen(navigator: DestinationsNavigator) {

    val movieList = MainScreenState().pagingMovie
    val context = LocalContext.current

    var sizeImage by remember { mutableStateOf(IntSize.Zero) }

    val movieListItems: LazyPagingItems<MovieElementModel> = movieList.collectAsLazyPagingItems()

    LaunchedEffect(key1 = Unit) {
        ViewModels.mainScreen.getMoviesByPage(1)
        ViewModels.mainScreen.getPromotedMovie()
        ViewModels.profileScreen.onClickProfile(context = context, navigator)
    }

    Scaffold(bottomBar = { BottomBar(navigator, 0) }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(halfDefaultPadding)
        ) {

            CoroutineScope(Dispatchers.IO).launch {
                ViewModels.mainScreen.getFavourites(context)
                Log.i("favouritesRequest", "favouritesRequest")
            }

            //if(ViewModel.mainScreen.currentM)
            item {
                Box {
                    AsyncImage(
                        model = ViewModels.mainScreen.promotedMovie,
                        contentDescription = "",
                        modifier = Modifier
                            .onGloballyPositioned { sizeImage = it.size }
                            .height(320.dp).fillMaxWidth(), contentScale = ContentScale.Crop)
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent, Color.Black
                                    ),
                                    startY = sizeImage.height.toFloat() / 1.55f,
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
                            buttonText = "????????????????",
                            paddingValues = PaddingValues(horizontal = 110.dp),
                            backgroundColor = logInButtonColor,
                            textColor = Color.White,
                            contentPadding = PaddingValues(12.dp)
                        ) {
                            CoroutineScope(Dispatchers.Main).launch {
                                ViewModels.mainScreen.onClickMovie(
                                    ViewModels.mainScreen.movieList[0].id, navigator
                                )
                            }
                        }
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxSize()
                ) {
                    if (ViewModels.mainScreen.isFavourites.value) {
                        Favourites(context, navigator)
                    }
                    SectionText(text = "??????????????", paddingValues = doubleDefaultTopPadding)
                }
            }
            itemsIndexed(movieListItems) { index, item ->
                if (index != 0) {
                    item?.let {
                        GalleryElement(navigator = navigator, movie = it)
                    }
                }

            }
            item {
                Spacer(modifier = Modifier.height(70.dp))
            }
        }
    }
}

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun Favourites(context: Context, navigator: DestinationsNavigator) {


    ViewModels.mainScreen.lazyListState = rememberLazyListState()
    SectionText(text = "??????????????????", paddingValues = mainScreenLazyPadding)
    LazyRow(
        modifier = Modifier.padding(top = 22.dp),
        state = ViewModels.mainScreen.lazyListState,
        horizontalArrangement = Arrangement.spacedBy(defaultPadding)
    ) {
        itemsIndexed(ViewModels.mainScreen.favouriteMoviesList.value) { index, movie ->
            if (index == ViewModels.mainScreen.lazyListState.firstVisibleItemIndex + 1) {
                FavouritesElement(
                    context = context, movie = movie, navigator = navigator, modifier = Modifier
                        .height(172.dp)
                        .width(120.dp)
                )
            } else {
                FavouritesElement(
                    context = context,
                    movie = movie,
                    navigator = navigator,
                    modifier = Modifier
                        .height(144.dp)
                        .width(100.dp)
                )
            }
        }
    }
}

@Composable
fun FavouritesElement(
    context: Context,
    movie: MovieModel,
    navigator: DestinationsNavigator,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        IconButton(onClick = {
            CoroutineScope(Dispatchers.Main).launch {
                ViewModels.mainScreen.onClickMovie(id = movie.id, navigator)
            }
        }) {
            AsyncImage(
                model = movie.poster,
                contentDescription = "",
                modifier = modifier,
                contentScale = ContentScale.FillWidth
            )
        }
        IconButton(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    ViewModels.mainScreen.onClickDelete(context, movie.id)
                    ViewModels.mainScreen.getFavourites(context)
                }
            }, modifier = Modifier
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

@Composable
fun GalleryElement(
    navigator: DestinationsNavigator,
    movie: MovieElementModel
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
            IconButton(onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    Log.i("Clicked", "Clicked Movie")
                    ViewModels.mainScreen.onClickMovie(movie.id, navigator)
                }
            }) {
                if (movie.poster != null) {
                    AsyncImage(
                        model = movie.poster,
                        contentDescription = "", modifier = Modifier
                            .height(144.dp)
                            .width(100.dp), contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Column(modifier = Modifier.fillMaxHeight()) {
                // ???????????????? ????????????
                if (movie.name != null) {
                    Text(
                        text = movie.name,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(
                            sectionButtonFontWeight
                        )
                    )
                }
                // ?????? ?? ???????? ????????????
                if (movie.country != null) {
                    Text(
                        text = "${movie.year} ??? ${movie.country}",
                        color = Color.White,
                        fontSize = movieInfoFontSize,
                        fontWeight = FontWeight(defaultFontWeight),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                } else {
                    Text(
                        text = "$movie.year",
                        color = Color.White,
                        fontSize = movieInfoFontSize,
                        fontWeight = FontWeight(defaultFontWeight),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                // ??????????
                if (movie.genres != null) {
                    Text(
                        text = ConvertMovieListUseCase().toString(movie.genres),
                        color = Color.White,
                        fontSize = movieInfoFontSize,
                        fontWeight = FontWeight(defaultFontWeight),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(43.dp))
                // ??????????????
                Text(
                    text = if (movie.reviews == null) "0" else GetMovieRatingUseCase().getRating(
                        movie.reviews
                    ).toString(),
                    modifier = Modifier
                        .background(
                            if (movie.reviews == null) Color.LightGray else MakeColorRatingUseCase().makeColor(
                                GetMovieRatingUseCase().getRating(movie.reviews)
                            ), shape = RoundedCornerShape(16.dp)
                        )
                        .size(56.dp, 28.dp)
                        .padding(vertical = 4.dp),
                    color = Color.White,
                    fontSize = buttonTextSize,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
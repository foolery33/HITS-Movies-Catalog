package com.example.myapplication.screen.main_screen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.Repositories
import com.example.myapplication.domain.ViewModel
import com.example.myapplication.screen.destinations.MovieScreenDestination
import com.example.myapplication.ui.theme.*
import com.example.myapplication.view.BottomBar
import com.example.myapplication.view.ButtonView
import com.example.myapplication.view.SectionText
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun MainScreen(navigator: DestinationsNavigator) {

    val context = LocalContext.current

    var sizeImage by remember { mutableStateOf(IntSize.Zero) }

    Scaffold(bottomBar = { BottomBar(navigator, 0) }) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(halfDefaultPadding)
        ) {
            /*CoroutineScope(Dispatchers.IO).launch {
                ViewModel.mainScreen.getFavourites(context)
                withContext(Dispatchers.Main) {
                    ViewModel.mainScreen.isFavourites.value = ViewModel.mainScreen.favouriteMovies.movies.isNotEmpty()
                }
            }*/
            item {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.magicians_poster),
                        contentDescription = "",
                        modifier = Modifier
                            .onGloballyPositioned { sizeImage = it.size }
                            .fillMaxWidth(), contentScale = ContentScale.FillWidth)
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
                            textColor = Color.White,
                            contentPadding = PaddingValues(12.dp)
                        ) {
                            Log.i(
                                "tokenValue",
                                Repositories.authRepository.getUserToken(context).token
                            )
                            navigator.navigate(MovieScreenDestination(1))
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
                    if(ViewModel.mainScreen.isFavourites.value) {
                        Favourites(navigator)
                    }
                    SectionText(text = "Галерея", paddingValues = doubleDefaultTopPadding)
                }
            }
            items(5) {
                GalleryElement(
                    navigator,
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
fun Favourites(navigator: DestinationsNavigator) {

    SectionText(text = "Избранное", paddingValues = mainScreenLazyPadding)
    LazyRow(
        modifier = Modifier.padding(top = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(defaultPadding)
    ) {
        itemsIndexed(ViewModel.mainScreen.favouriteMovies.movies) { index, movie ->
            Box(
                modifier = Modifier
                    .height(144.dp)
                    .width(100.dp)
            ) {
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        Log.i("clicked", "clicked")
                        ViewModel.mainScreen.onClickMovie(id = movie.id)
                        navigator.navigate(MovieScreenDestination(index))
                    }
                }) {
                    AsyncImage(
                        model = movie.poster, contentDescription = "", modifier = Modifier
                            .height(144.dp)
                            .width(100.dp), contentScale = ContentScale.FillWidth
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
    navigator: DestinationsNavigator,
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
            IconButton(onClick = {
                navigator.navigate(MovieScreenDestination(1))
            }) {
                Image(
                    painter = painterResource(id = R.drawable.movie_img),
                    contentDescription = "", modifier = Modifier
                        .height(144.dp)
                        .width(100.dp), contentScale = ContentScale.FillWidth
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
                Text(
                    text = rating.toString(),
                    modifier = Modifier
                        .background(Color.Green, shape = RoundedCornerShape(16.dp))
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
package com.example.myapplication.viewmodel.main_screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.domain.ViewModel
import com.example.myapplication.domain.main_screen.use_cases.GetFavouritesUseCase
import com.example.myapplication.network.favourite_movies.FavouritesResponse
import kotlinx.coroutines.delay

class MainScreenState {

    lateinit var favouriteMovies: FavouritesResponse
    var isFavourites = mutableStateOf(false)

    suspend fun getFavourites(
        context: Context
    ) {
        try {
            favouriteMovies = GetFavouritesUseCase().getFavourites(context = context)
            isFavourites.value = favouriteMovies.movies.isNotEmpty()
        } catch (e: Exception) {
            Log.i("favourites", e.message.toString())
        }
    }

}
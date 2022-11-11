package com.example.myapplication.viewmodel.main_screen

import android.content.Context
import android.util.Log
import com.example.myapplication.data.Repositories
import com.example.myapplication.domain.main_screen.use_cases.GetFavouritesUseCase
import com.example.myapplication.network.favourite_movies.FavouritesResponse

class MainScreenState {

    var favouriteMovies: List<FavouritesResponse> = emptyList()

    suspend fun getFavourites(
        context: Context
    ) {
        try {
            favouriteMovies = GetFavouritesUseCase().getFavourites(context = context)
        } catch (e: Exception) {
            Log.i("favourites", e.message.toString())
        }
    }

}
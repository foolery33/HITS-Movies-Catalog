package com.example.myapplication.domain.main_screen

import android.content.Context
import com.example.myapplication.network.favourite_movies.FavouritesResponse

interface FavouriteMoviesRepository {

    suspend fun getFavourites(context: Context): List<FavouritesResponse>

}
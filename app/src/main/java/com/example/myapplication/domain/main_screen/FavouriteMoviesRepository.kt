package com.example.myapplication.domain.main_screen

import android.content.Context
import com.example.myapplication.network.favourite_movies.FavouritesResponse
import com.example.myapplication.network.favourite_movies.MovieModel

interface FavouriteMoviesRepository {

    suspend fun getFavourites(context: Context): FavouritesResponse

}
package com.example.myapplication.domain.main_screen.repository

import android.content.Context
import com.example.myapplication.network.favourite_movies.FavouriteMovieList

interface FavouriteMoviesRepository {

    suspend fun getFavourites(context: Context): FavouriteMovieList

    suspend fun addToFavourites(context: Context, id: String)

    suspend fun deleteFromFavourites(context: Context, id: String)

}
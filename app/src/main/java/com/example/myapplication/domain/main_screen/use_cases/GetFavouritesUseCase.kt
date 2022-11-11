package com.example.myapplication.domain.main_screen.use_cases

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.network.favourite_movies.FavouriteMovieList

class GetFavouritesUseCase {

    private val repository = Repositories.favouriteMoviesRepository

    suspend fun getFavourites(context: Context): FavouriteMovieList {
        return repository.getFavourites(context)
    }

}
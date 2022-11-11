package com.example.myapplication.domain.main_screen.use_cases

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.network.favourite_movies.FavouritesResponse
import com.example.myapplication.network.favourite_movies.MovieModel
import kotlinx.coroutines.delay

class GetFavouritesUseCase {

    private val repository = Repositories.favouriteMoviesRepository

    suspend fun getFavourites(context: Context): FavouritesResponse {
        return repository.getFavourites(context)
    }

}
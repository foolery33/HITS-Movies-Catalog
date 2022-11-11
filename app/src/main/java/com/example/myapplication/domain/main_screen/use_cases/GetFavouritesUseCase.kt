package com.example.myapplication.domain.main_screen.use_cases

import android.content.Context
import android.util.Log
import com.example.myapplication.data.Repositories
import com.example.myapplication.network.favourite_movies.FavouritesResponse

class GetFavouritesUseCase {

    private val repository = Repositories.favouriteMoviesRepository

    suspend fun getFavourites(context: Context): List<FavouritesResponse> {
        return repository.getFavourites(context)
    }

}
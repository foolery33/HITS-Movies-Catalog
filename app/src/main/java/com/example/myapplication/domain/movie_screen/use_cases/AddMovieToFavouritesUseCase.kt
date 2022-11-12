package com.example.myapplication.domain.movie_screen.use_cases

import android.content.Context
import com.example.myapplication.data.Repositories

class AddMovieToFavouritesUseCase {

    private val repository = Repositories.favouriteMoviesRepository

    suspend fun addToFavourites(context: Context, id: String) {
        repository.addToFavourites(context, id)
    }

}
package com.example.myapplication.domain.main_screen.use_cases

import android.content.Context
import com.example.myapplication.data.Repositories

class DeleteMovieFromFavouritesUseCase {

    private val repository = Repositories.favouriteMoviesRepository

    suspend fun deleteMovie(context: Context, id: String) {
        repository.deleteFromFavourites(context = context, id = id)
    }

}
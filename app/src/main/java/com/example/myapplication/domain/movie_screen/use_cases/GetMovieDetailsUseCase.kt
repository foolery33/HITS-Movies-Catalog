package com.example.myapplication.domain.movie_screen.use_cases

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.network.movie.MovieDetailsModel

class GetMovieDetailsUseCase {

    private val repository = Repositories.movieRepository

    suspend fun getDetails(
        id: String
    ): MovieDetailsModel {
        return repository.getMovieDetails(id = id)
    }

}
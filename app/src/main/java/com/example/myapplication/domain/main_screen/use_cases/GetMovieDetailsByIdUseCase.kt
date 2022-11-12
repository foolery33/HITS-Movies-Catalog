package com.example.myapplication.domain.main_screen.use_cases

import com.example.myapplication.domain.ViewModels
import com.example.myapplication.network.movie.MovieDetailsModel

class GetMovieDetailsByIdUseCase {

    fun getDetails(id: String): MovieDetailsModel {
        return ViewModels.mainScreen.movies[id]!!
    }

}
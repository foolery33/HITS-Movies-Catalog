package com.example.myapplication.domain.movie_screen

import com.example.myapplication.data.Repositories
import com.example.myapplication.domain.ViewModel
import com.example.myapplication.network.movie.MovieDetailsModel

class GetFavouriteFilmDetailsUseCase {

    fun getDetails(id: Int): MovieDetailsModel {
        return ViewModel.mainScreen.movies[ViewModel.mainScreen.favouriteMovies.movies[id].id]!!
    }

}
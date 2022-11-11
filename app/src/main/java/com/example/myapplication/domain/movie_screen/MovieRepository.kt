package com.example.myapplication.domain.movie_screen

import com.example.myapplication.network.movie.MovieDetailsModel

interface MovieRepository {

    suspend fun getMovieDetails(id: String): MovieDetailsModel

}
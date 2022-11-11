package com.example.myapplication.domain.movie_screen

import com.example.myapplication.network.movie.MovieDetailsModel
import com.example.myapplication.network.movie.MoviesPagedListModel

interface MovieRepository {

    suspend fun getMovieDetails(id: String): MovieDetailsModel

    suspend fun getMoviesPage(page: Int): MoviesPagedListModel

}
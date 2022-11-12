package com.example.myapplication.domain.main_screen.use_cases

import com.example.myapplication.data.Repositories
import com.example.myapplication.network.movie.MoviesPagedListModel

class GetMoviesPageUseCase {

    val repository = Repositories.movieRepository

    suspend fun getPage(
        page: Int
    ): MoviesPagedListModel {
        return repository.getMoviesPage(page = page)
    }

}
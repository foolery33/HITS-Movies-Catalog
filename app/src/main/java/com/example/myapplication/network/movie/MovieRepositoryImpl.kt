package com.example.myapplication.network.movie

import android.content.Context
import com.example.myapplication.domain.movie_screen.MovieRepository
import com.example.myapplication.network.Network

class MovieRepositoryImpl: MovieRepository {

    private val api: MovieApi = Network.getMovieApi()

    override suspend fun getMovieDetails(id: String) = api.getMovieDetails(id)

}
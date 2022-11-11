package com.example.myapplication.network.movie

import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("/api/movies/details/{id}")
    suspend fun getMovieDetails(@Path("id") id: String): MovieDetailsModel
}
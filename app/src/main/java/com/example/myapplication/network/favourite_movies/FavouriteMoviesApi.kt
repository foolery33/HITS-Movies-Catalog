package com.example.myapplication.network.favourite_movies

import retrofit2.http.GET
import retrofit2.http.Header

interface FavouriteMoviesApi {
    @GET("/api/favorites")
    suspend fun getFavouriteMovies(@Header("Authorization") token: String): List<FavouritesResponse>
}
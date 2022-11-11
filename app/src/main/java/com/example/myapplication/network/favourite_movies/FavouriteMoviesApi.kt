package com.example.myapplication.network.favourite_movies

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface FavouriteMoviesApi {

    @GET("/api/favorites")
    suspend fun getFavouriteMovies(@Header("Authorization") token: String): FavouriteMovieList
    @POST("/api/favorites/{id}/add")
    suspend fun addMovieToFavourites(@Header("Authorization") token: String, @Path("id") id: String)
    @DELETE("/api/favorites/{id}/delete")
    suspend fun deleteMovieFromFavourites(@Header("Authorization") token: String, @Path("id") id: String)

}
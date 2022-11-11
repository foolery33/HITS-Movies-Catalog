package com.example.myapplication.network.favourite_movies

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.domain.main_screen.repository.FavouriteMoviesRepository
import com.example.myapplication.network.Network

class FavouriteMoviesRepositoryImpl : FavouriteMoviesRepository {

    private val api: FavouriteMoviesApi = Network.getFavouriteMoviesApi()

    override suspend fun getFavourites(context: Context): FavouriteMovieList =
        api.getFavouriteMovies(token = "Bearer " + Repositories.authRepository.getUserToken(context = context).token)

    override suspend fun deleteFromFavourites(context: Context, id: String) {
        api.deleteMovieFromFavourites(token = "Bearer " + Repositories.authRepository.getUserToken(context = context).token, id = id)
    }

}
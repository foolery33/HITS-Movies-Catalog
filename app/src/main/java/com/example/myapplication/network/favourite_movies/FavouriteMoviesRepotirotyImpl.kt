package com.example.myapplication.network.favourite_movies

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.domain.main_screen.FavouriteMoviesRepository
import com.example.myapplication.network.Network

class FavouriteMoviesRepotirotyImpl : FavouriteMoviesRepository {

    private val api: FavouriteMoviesApi = Network.getFavouriteMoviesApi()

    override suspend fun getFavourites(context: Context): FavouritesResponse =
        api.getFavouriteMovies(token = "Bearer " + Repositories.authRepository.getUserToken(context = context).token)
}
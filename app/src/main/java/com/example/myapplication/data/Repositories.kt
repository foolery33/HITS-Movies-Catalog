package com.example.myapplication.data

import com.example.myapplication.domain.main_screen.repository.FavouriteMoviesRepository
import com.example.myapplication.domain.movie_screen.MovieRepository
import com.example.myapplication.domain.sign_up_screen.repository.AuthRepository
import com.example.myapplication.domain.sign_up_screen.repository.UserRepository
import com.example.myapplication.network.authorization.AuthRepositoryImpl
import com.example.myapplication.network.favourite_movies.FavouriteMoviesRepositoryImpl
import com.example.myapplication.network.movie.MovieRepositoryImpl
import com.example.myapplication.network.profile.UserRepositoryImpl

object Repositories {

    val authRepository: AuthRepository by lazy { AuthRepositoryImpl() }
    val userRepository: UserRepository by lazy { UserRepositoryImpl() }
    val favouriteMoviesRepository: FavouriteMoviesRepository by lazy { FavouriteMoviesRepositoryImpl() }
    val movieRepository: MovieRepository by lazy { MovieRepositoryImpl() }

}
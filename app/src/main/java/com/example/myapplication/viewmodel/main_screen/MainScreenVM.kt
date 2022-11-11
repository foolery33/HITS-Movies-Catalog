package com.example.myapplication.viewmodel.main_screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.domain.main_screen.use_cases.GetFavouritesUseCase
import com.example.myapplication.domain.movie_screen.use_cases.GetMovieDetailsUseCase
import com.example.myapplication.network.favourite_movies.FavouriteMovieList
import com.example.myapplication.network.movie.MovieDetailsModel

class MainScreenState {

    lateinit var favouriteMovies: FavouriteMovieList
    var isFavourites = mutableStateOf(false)

    var movies: MutableMap<String, MovieDetailsModel> = mutableMapOf()

    suspend fun getFavourites(
        context: Context
    ) {
        try {
            favouriteMovies = GetFavouritesUseCase().getFavourites(context = context)
            isFavourites.value = favouriteMovies.movies.isNotEmpty()
        } catch (e: Exception) {
            Log.i("favourites", e.message.toString())
        }
    }

    suspend fun onClickMovie(id: String) {
        try {
            val response: MovieDetailsModel = GetMovieDetailsUseCase().getDetails(id)
            movies[response.id] = response
            Log.i("movieId1", response.toString())
        } catch (e: Exception) {
            Log.i("errorList", "some movie detail error")
        }
    }

}
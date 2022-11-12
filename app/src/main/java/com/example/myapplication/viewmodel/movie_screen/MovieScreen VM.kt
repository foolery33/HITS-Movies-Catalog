package com.example.myapplication.viewmodel.movie_screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.domain.movie_screen.use_cases.AddMovieToFavouritesUseCase
import com.example.myapplication.network.movie.ReviewModel

class MovieScreenState {

    val isFavourite = mutableStateOf(false)
    val isMadeReview = mutableStateOf(false)
    var reviewList: MutableState<List<ReviewModel>?> = mutableStateOf(listOf())

    suspend fun onClickAdd(context: Context, id: String) {
        try {
            AddMovieToFavouritesUseCase().addToFavourites(context = context, id = id)
            isFavourite.value = true
        } catch(e: Exception) {
            Log.i("errorList", "some add movie error")
        }
    }

}
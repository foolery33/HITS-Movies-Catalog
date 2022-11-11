package com.example.myapplication.viewmodel.movie_screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.domain.movie_screen.use_cases.AddMovieToFavouritesUseCase

class MovieScreenState {

    val reviewDialogState = ReviewDialogState()
    val enabled = mutableStateOf(true)
    val isFavourite = mutableStateOf(false)

    suspend fun onClickAdd(context: Context, id: String) {
        try {
            AddMovieToFavouritesUseCase().addToFavourites(context = context, id = id)
            isFavourite.value = true
        } catch(e: Exception) {
            Log.i("errorList", "some add movie error")
        }
    }

}
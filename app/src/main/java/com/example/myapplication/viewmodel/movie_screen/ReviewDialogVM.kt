package com.example.myapplication.viewmodel.movie_screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.domain.general_use_cases.MakeToastUseCase
import com.example.myapplication.domain.movie_screen.use_cases.AddReviewUseCase
import com.example.myapplication.domain.movie_screen.use_cases.DeleteReviewUseCase
import com.example.myapplication.domain.movie_screen.use_cases.EditReviewUseCase

class ReviewDialogState {
    val showDialog = mutableStateOf(false)
    val textData = mutableStateOf("")
    val ratingValue = mutableStateOf(0)
    val checkedState = mutableStateOf(false)

    var action = ""

    suspend fun onClickSave(context: Context, movieId: String, id: String) {
        if(action == "edit") {
            try {
                EditReviewUseCase().editReview(
                    context,
                    reviewText = textData.value,
                    rating = ratingValue.value,
                    isAnonymous = checkedState.value,
                    movieId = movieId,
                    id = id
                )
            } catch (e: Exception) {
                MakeToastUseCase().show(context, "Поле отзыва не должно оставаться пустым")
            }
        }
        else {
            try {
                AddReviewUseCase().addReview(
                    context = context,
                    reviewText = textData.value,
                    rating = ratingValue.value,
                    isAnonymous = checkedState.value,
                    movieId = movieId
                )
            } catch (e: Exception) {
                MakeToastUseCase().show(context, "Вы уже оставляли отзыв к этому фильму")
            }
        }
    }

    suspend fun onClickDelete(context: Context, movieId: String, id: String) {
        try {
            DeleteReviewUseCase().deleteReview(context = context, movieId = movieId, id = id)
        } catch(e: Exception) {
            Log.i("errorList", "some delete review error")
        }
    }
}
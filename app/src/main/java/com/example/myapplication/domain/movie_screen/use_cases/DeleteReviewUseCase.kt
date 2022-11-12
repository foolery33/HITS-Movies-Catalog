package com.example.myapplication.domain.movie_screen.use_cases

import android.content.Context
import com.example.myapplication.data.Repositories

class DeleteReviewUseCase {

    private val repository = Repositories.reviewRepository

    suspend fun deleteReview(
        context: Context,
        movieId: String,
        id: String
    ) {
        repository.deleteReview(context = context, movieId = movieId, id = id)
    }

}
package com.example.myapplication.domain.movie_screen.use_cases

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.network.review.ReviewModifyModel

class EditReviewUseCase {

    private val repository = Repositories.reviewRepository

    suspend fun editReview(
        context: Context,
        reviewText: String,
        rating: Int,
        isAnonymous: Boolean,
        movieId: String,
        id: String
    ) {

        val reviewModifyModel = ReviewModifyModel(
            reviewText = reviewText,
            rating = rating,
            isAnonymous = isAnonymous
        )
        repository.editReview(context = context, requestBody = reviewModifyModel, movieId = movieId, id = id)
    }

}
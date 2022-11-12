package com.example.myapplication.domain.movie_screen.use_cases

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.network.review.ReviewModifyModel

class AddReviewUseCase {

    private val repository = Repositories.reviewRepository

    suspend fun addReview(
        context: Context,
        reviewText: String,
        rating: Int,
        isAnonymous: Boolean,
        movieId: String
    ) {

        val reviewModifyModel = ReviewModifyModel(
            reviewText = reviewText,
            rating = rating,
            isAnonymous = isAnonymous
        )
        repository.addReview(context = context, requestBody = reviewModifyModel, movieId = movieId)
    }

}
package com.example.myapplication.network.review

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.network.Network

class ReviewRepositoryImpl : ReviewRepository {

    private val api: ReviewApi = Network.getReviewApi()

    override suspend fun addReview(
        context: Context,
        requestBody: ReviewModifyModel,
        movieId: String
    ) {
        api.addReview(
            token = "Bearer " + Repositories.authRepository.getUserToken(context).token,
            body = requestBody,
            movieId = movieId,
        )
    }

    override suspend fun editReview(
        context: Context,
        requestBody: ReviewModifyModel,
        movieId: String,
        id: String
    ) {
        api.editReview(
            token = "Bearer " + Repositories.authRepository.getUserToken(context).token,
            body = requestBody,
            movieId = movieId,
            id = id
        )
    }

    override suspend fun deleteReview(context: Context, movieId: String, id: String) {
        api.deleteReview(
            token = "Bearer " + Repositories.authRepository.getUserToken(context).token,
            movieId = movieId,
            id = id
        )
    }
}
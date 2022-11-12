package com.example.myapplication.network.review

import android.content.Context

interface ReviewRepository {

    suspend fun addReview(context: Context, requestBody: ReviewModifyModel, movieId: String)

    suspend fun editReview(context: Context, requestBody: ReviewModifyModel, movieId: String, id: String)

    suspend fun deleteReview(context: Context, movieId: String, id: String)

}
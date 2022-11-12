package com.example.myapplication.network.review

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReviewApi {
    @POST("/api/movie/{movieId}/review/add")
    suspend fun addReview(
        @Header("Authorization") token: String,
        @Body body: ReviewModifyModel,
        @Path("movieId") movieId: String
    )
    @PUT("/api/movie/{movieId}/review/{id}/edit")
    suspend fun editReview(@Header("Authorization") token: String,
                           @Body body: ReviewModifyModel,
                           @Path("movieId") movieId: String,
                           @Path("id") id: String
    )
    @DELETE("/api/movie/{movieId}/review/{id}/delete")
    suspend fun deleteReview(@Header("Authorization") token: String,
                             @Path("movieId") movieId: String,
                             @Path("id") id: String)
}
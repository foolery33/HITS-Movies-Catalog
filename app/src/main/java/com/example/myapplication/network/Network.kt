@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.myapplication.network

import com.example.myapplication.network.authorization.AuthApi
import com.example.myapplication.network.authorization.AuthInterceptor
import com.example.myapplication.network.favourite_movies.FavouriteMoviesApi
import com.example.myapplication.network.movie.MovieApi
import com.example.myapplication.network.profile.UserApi
import com.example.myapplication.network.review.ReviewApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object Network {

    private const val BASE_URL = "https://react-midterm.kreosoft.space/"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    var token: String = ""

    private fun getHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(AuthInterceptor())

            val logLevel = HttpLoggingInterceptor.Level.BODY
            addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
        }

        return client.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .client(getHttpClient())
            .build()
    }

    private val retrofit: Retrofit = getRetrofit()

    fun getAuthApi(): AuthApi = retrofit.create(AuthApi::class.java)
    fun getUserApi(): UserApi = retrofit.create(UserApi::class.java)
    fun getFavouriteMoviesApi(): FavouriteMoviesApi = retrofit.create(FavouriteMoviesApi::class.java)
    fun getMovieApi(): MovieApi = retrofit.create(MovieApi::class.java)
    fun getReviewApi(): ReviewApi = retrofit.create(ReviewApi::class.java)

}
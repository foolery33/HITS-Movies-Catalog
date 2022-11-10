package com.example.myapplication.network.authorization

import com.example.myapplication.network.Network
import com.example.myapplication.network.Network.token
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request().newBuilder().apply {
            addHeader("accept", "application/json")
            addHeader("content-Type", "")
            Network.token?.let {
                addHeader("Access-Token", "Bearer: ${token}")
            }
        }.build()

        var response: Response? = null

        return try {
            response = chain.proceed(request)
            response
        } catch (e: Exception) {
            response?.close()
            chain.proceed(request)
        }

    }
}
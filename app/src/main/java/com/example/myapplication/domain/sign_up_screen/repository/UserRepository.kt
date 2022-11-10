package com.example.myapplication.domain.sign_up_screen.repository

import android.content.Context
import com.example.myapplication.network.profile.ProfileRequestBody
import com.example.myapplication.network.profile.ProfileResponse

interface UserRepository {

    suspend fun getUserProfile(context: Context): ProfileResponse

    suspend fun changeUserProfile(context: Context, body: ProfileRequestBody)

}
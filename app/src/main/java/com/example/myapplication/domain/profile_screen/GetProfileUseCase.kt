package com.example.myapplication.domain.profile_screen

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.network.profile.ProfileResponse

class GetProfileUseCase {

    private val repository = Repositories.userRepository

    suspend fun getProfile(
        context: Context
    ): ProfileResponse {
        return repository.getUserProfile(context)
    }

}
package com.example.myapplication.domain.profile_screen

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.domain.ViewModel
import com.example.myapplication.network.profile.ProfileRequestBody

class SaveProfileUseCase {

    private val repository = Repositories.userRepository

    suspend fun saveProfile(
        id: String,
        nickName: String,
        email: String,
        avatarLink: String,
        name: String,
        birthDate: String,
        gender: Int,
        context: Context
    ) {
        val profileRequestBody = ProfileRequestBody(
            id = id,
            nickName = nickName,
            email = email,
            avatarLink = avatarLink,
            name = name,
            birthDate = birthDate,
            gender = gender
        )

        repository.changeUserProfile(context, profileRequestBody)
    }

}
package com.example.myapplication.network.profile

import android.content.Context
import com.example.myapplication.data.Repositories
import com.example.myapplication.domain.sign_up_screen.repository.UserRepository
import com.example.myapplication.network.Network

class UserRepositoryImpl : UserRepository {

    private val api: UserApi = Network.getUserApi()

    override suspend fun getUserProfile(context: Context): ProfileResponse =
        api.getProfile(token = "Bearer " + Repositories.authRepository.getUserToken(context).token)

    override suspend fun changeUserProfile(context: Context, body: ProfileRequestBody) {
        api.setProfile(
            token = "Bearer " + Repositories.authRepository.getUserToken(context).token,
            body
        )
    }

}
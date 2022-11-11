package com.example.myapplication.network.authorization

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.domain.sign_up_screen.repository.AuthRepository
import com.example.myapplication.model.LoginRequestBody
import com.example.myapplication.network.Network
import com.example.myapplication.network.authorization.SharedPreferencesHandler.sharedPreference
import com.example.myapplication.network.authorization.SharedPreferencesHandler.userToken

private const val SHARED_PREFS_NAME = "shared_prefs_name"

class AuthRepositoryImpl : AuthRepository {

    private val api: AuthApi = Network.getAuthApi()

/*    suspend fun register(body: RegisterRequestBody): Flow<TokenResponse> = flow {
        val tokenData = api.register(body)
        Network.token = tokenData
        emit(tokenData)
    }.flowOn(Dispatchers.IO)*/

    override suspend fun register(body: RegisterRequestBody): TokenResponse = api.register(body)

    override suspend fun login(body: LoginRequestBody): TokenResponse = api.login(body)

    override suspend fun logout(): LogoutResponse = api.logout()

    @SuppressLint("CommitPrefEdits")
    override fun saveUserToken(context: Context, tokenResponse: TokenResponse) {
        val preference = sharedPreference(context, SHARED_PREFS_NAME)
        preference.userToken = tokenResponse.token
    }

    override fun getUserToken(context: Context): TokenResponse {
        val preference = sharedPreference(context, SHARED_PREFS_NAME)
        return TokenResponse(token = preference.userToken!!)
    }
}

object SharedPreferencesHandler {

    private const val USER_TOKEN = "user_token"

    fun sharedPreference(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val edit = edit()
        operation(edit)
        edit.apply()
    }

    var SharedPreferences.userToken
        get() = getString(USER_TOKEN, "")
        set(value) {
            edit {
                it.putString(USER_TOKEN, value)
            }
        }

    var SharedPreferences.clearToken
    get() = run { }
    set(value) {
        edit {
            it.clear()
        }
    }
}
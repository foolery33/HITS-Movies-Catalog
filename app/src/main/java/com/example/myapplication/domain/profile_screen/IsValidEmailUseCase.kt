package com.example.myapplication.domain.profile_screen

import android.text.TextUtils

class IsValidEmailUseCase {

    fun isValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}
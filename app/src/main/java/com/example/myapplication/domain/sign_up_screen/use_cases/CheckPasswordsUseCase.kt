package com.example.myapplication.domain.sign_up_screen.use_cases

class CheckPasswordsUseCase {

    fun check(string1: String, string2: String):Boolean {
        return string1 == string2
    }

}
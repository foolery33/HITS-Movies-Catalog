package com.example.myapplication.domain.movie_screen.use_cases

class ConvertMoneyViewUseCase {

    fun convert(value: String): String {

        val spacesCount = ((value.length - 1) / 3)
        var newString = value

        for (i in 1 until spacesCount + 1) {
            newString = newString.replaceRange(value.length - i * 3, value.length - i * 3, " ")
        }

        return newString
    }

}
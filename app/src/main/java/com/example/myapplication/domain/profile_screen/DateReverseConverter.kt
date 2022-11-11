package com.example.myapplication.domain.profile_screen

import android.annotation.SuppressLint
import java.util.*

class DateReverseConverter {

    @SuppressLint("SimpleDateFormat")
    fun toProfileFormat(date: String): String {

        val currentDate = date.subSequence(0, 10)

        return currentDate.substring(8, 10) + "." + currentDate.substring(
            5,
            7
        ) + "." + currentDate.substring(0, 4)
    }

}
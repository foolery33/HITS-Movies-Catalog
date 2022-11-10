package com.example.myapplication.domain.profile_screen

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateReverseConverter {

    @SuppressLint("SimpleDateFormat")
    fun toProfileFormat(date: String): String {

        val currentDate = date.subSequence(0, 10)

        val currentFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.UK)
        val finalFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy")

        return currentFormat.format(finalFormat.parse(currentDate.toString())!!)
    }

}
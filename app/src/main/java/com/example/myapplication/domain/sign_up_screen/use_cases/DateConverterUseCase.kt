package com.example.myapplication.domain.sign_up_screen.use_cases

import android.annotation.SuppressLint
import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateConverterUseCase {

    @SuppressLint("SimpleDateFormat")
    fun toJsonFormat(date: String): String {

        val currentFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy")
        val finalFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.UK)

        val jsonDate: String = finalFormat.format(currentFormat.parse(date)!!)

        Log.i("jsonDate", jsonDate + "T08:46:29.129Z")
        return jsonDate + "T08:46:29.129Z"
    }

}
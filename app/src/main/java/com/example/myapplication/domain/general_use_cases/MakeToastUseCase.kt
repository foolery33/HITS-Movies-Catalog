package com.example.myapplication.domain.general_use_cases

import android.content.Context
import android.widget.Toast
import java.time.Duration

class MakeToastUseCase {

    fun show(context: Context, text: String) {
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_LONG + Toast.LENGTH_LONG
        ).show()
    }

}
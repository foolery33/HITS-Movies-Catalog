package com.example.myapplication.domain.main_screen.use_cases

import android.util.Log
import androidx.compose.ui.graphics.Color
import okhttp3.internal.toHexString
import java.lang.Math.abs
import java.lang.Math.min

class MakeColorRatingUseCase {

    fun makeColor(rating: Double): Color {

        val intGreen = 0XFF00EC0A.toInt()
        val intRed = 0XF3FF2121.toInt()

        val diff = abs(intGreen - intRed)
        val step: Float = diff.toFloat() / 100

        val intResult = (min(intRed, intGreen) + step * rating).toInt()

        return Color(intResult)
    }

    fun hexToInt(hex: String): Long {

        var string = hex
        var result:Long = 0

        for (i in string.length - 1 downTo  0) {
            val letter = if(string[i].toString() == "F"){
                15
            }
            else {
                if(string[i].toString() == "E") {
                    14
                }
                else {
                    if(string[i].toString() == "D") {
                        13
                    }
                    else {
                        if(string[i].toString() == "C") {
                            12
                        }
                        else {
                            if(string[i].toString() == "B") {
                                11
                            }
                            else {
                                if(string[i].toString() == "A") {
                                    10
                                }
                                else {
                                    string[i].code
                                }
                            }
                        }
                    }
                }
            }
            result += letter * pow(16, i)
        }
        return result
    }

    fun pow(a: Int, b: Int): Int {
        if(b == 0) {
            return 1
        }
        else if(b == 1) {
            return a
        }
        else {
            var result = a
            for (i in 1 until b) {
                result *= a
            }
            return result
        }

    }

}
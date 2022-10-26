package com.example.myapplication.ui.theme

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun unfilledToFilledButton(
    areFilled: Boolean,
    filledButtonColor: Color,
    unfilledButtonColor: Color,
    filledTextColor: Color,
    unfilledTextColor: Color
): ButtonColors {
    return ButtonDefaults.outlinedButtonColors(
        backgroundColor = if (areFilled) filledButtonColor else unfilledButtonColor,
        contentColor = if (areFilled) filledTextColor else unfilledTextColor
    )
}
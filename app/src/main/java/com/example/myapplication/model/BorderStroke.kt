package com.example.myapplication.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun defaultButtonBorderStroke(
    isFilledButton: Boolean,
    filledStrokeColor: Color,
    unfilledColor: Color,
    filledStrokeSize: Int,
    unfilledStrokeSize: Int
): BorderStroke {
    return BorderStroke(
        if (isFilledButton) filledStrokeSize.dp else unfilledStrokeSize.dp,
        if (isFilledButton) filledStrokeColor else unfilledColor,
    )
}
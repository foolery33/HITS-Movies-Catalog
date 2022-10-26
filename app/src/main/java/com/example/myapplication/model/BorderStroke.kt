package com.example.myapplication.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun unfilledToFilledButtonBorderStroke(
    isFilledButton: Boolean,
    filledStrokeColor: Color,
    unfilledColor: Color,
    filledStrokeSize: Dp,
    unfilledStrokeSize: Dp
): BorderStroke {
    return BorderStroke(
        if (isFilledButton) filledStrokeSize else unfilledStrokeSize,
        if (isFilledButton) filledStrokeColor else unfilledColor,
    )
}
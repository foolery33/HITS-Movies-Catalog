package com.example.myapplication.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp),
)
val sexButtonMale = RoundedCornerShape(
    topEnd = 0.dp,
    topStart = 8.dp,
    bottomEnd = 0.dp,
    bottomStart = 8.dp
)

val sexButtonFemale = RoundedCornerShape(
    topEnd = 8.dp,
    topStart = 0.dp,
    bottomEnd = 8.dp,
    bottomStart = 0.dp
)

val defaultButtonShape = RoundedCornerShape(defaultCornerShape)
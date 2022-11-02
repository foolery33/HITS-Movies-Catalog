package com.example.myapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.baseWhite
import com.example.myapplication.ui.theme.textColor

@Composable
fun MovieGenre(genre: String) {
    Text(
        text = genre,
        modifier = Modifier
            .background(
                color = textColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 6.dp),
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = baseWhite
    )
}
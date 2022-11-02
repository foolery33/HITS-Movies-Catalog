package com.example.myapplication.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.aboutFilmColor

@Composable
fun AboutFilmRow(parameter: String, description: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.width(100.dp)) {
            Text(text = parameter, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = aboutFilmColor)
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = description, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.White)
        }
    }
}
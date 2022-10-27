package com.example.myapplication.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.myapplication.ui.theme.sectionButtonFontWeight
import com.example.myapplication.ui.theme.sectionTextSize
import com.example.myapplication.ui.theme.textColor

@Composable
fun SectionText(text: String, paddingValues: PaddingValues) {
    Text(
        text = text,
        color = textColor,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        fontSize = sectionTextSize,
        fontWeight = FontWeight(sectionButtonFontWeight)
    )
}
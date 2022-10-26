package com.example.myapplication.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication.ui.theme.*

@Composable
fun ButtonView(
    buttonText: String,
    paddingValues: PaddingValues
) {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues),
        content = {
            Text(
                text = buttonText,
                fontSize = buttonTextSize,
                fontWeight = FontWeight(buttonFontWeight)
            )
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = textColor
        ),
        contentPadding = buttonContentPaddings
    )
}
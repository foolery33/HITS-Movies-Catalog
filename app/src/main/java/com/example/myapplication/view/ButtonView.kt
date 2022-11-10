package com.example.myapplication.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.*

@Composable
fun ButtonView(
    buttonText: String,
    paddingValues: PaddingValues,
    backgroundColor: Color,
    textColor: Color,
    contentPadding: PaddingValues,
    onClickEvent: () -> Unit
) {
    Button(
        onClick = onClickEvent,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues),
        border = BorderStroke(width = 0.dp, color = backgroundColor),
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
        contentPadding = contentPadding,
        shape = defaultButtonShape,
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, hoveredElevation = 0.dp, focusedElevation = 0.dp)
    )
}
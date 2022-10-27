package com.example.myapplication.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication.model.unfilledToFilledButtonBorderStroke
import com.example.myapplication.ui.theme.*

@Composable
fun OutlinedButtonView(
    buttonText: String,
    areFilledFields: Boolean,
    paddingValues: PaddingValues
) {
    OutlinedButton(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues),
        content = {
            Text(
                text = buttonText, fontSize = buttonTextSize, fontWeight = FontWeight(
                    buttonFontWeight
                )
            )
        },
        colors = unfilledToFilledButton(
            areFilled = areFilledFields,
            filledButtonColor = logInButtonColor,
            unfilledButtonColor = backgroundColor,
            filledTextColor = Color.White,
            unfilledTextColor = textColor
        ),
        border = unfilledToFilledButtonBorderStroke(
            isFilledButton = areFilledFields,
            filledStrokeColor = logInButtonColor,
            unfilledColor = strokeColor,
            filledStrokeSize = filledButtonStrokeSize,
            unfilledStrokeSize = unfilledButtonStrokeSize
        ),
        shape = defaultButtonShape,
        contentPadding = buttonTextPaddings
    )
}
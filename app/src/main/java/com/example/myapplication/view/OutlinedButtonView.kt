package com.example.myapplication.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.*

@Composable
fun OutlinedButtonView(buttonText: String, areFilledFields: MutableState<Boolean>) {
    OutlinedButton(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 164.dp),
        content = { Text(buttonText, fontSize = 16.sp, fontWeight = FontWeight(500)) },
        colors = filledButton(
            areFilled = areFilledFields.value,
            filledButtonColor = logInButtonColor,
            unfilledButtonColor = backgroundColor,
            filledTextColor = Color.White,
            unfilledTextColor = textColor
        ),
        border = if (areFilledFields.value) BorderStroke(
            0.dp,
            logInButtonColor
        ) else BorderStroke(1.dp, strokeColor),
        shape = RoundedCornerShape(4.dp),
        contentPadding = buttonPaddingValues
    )
}
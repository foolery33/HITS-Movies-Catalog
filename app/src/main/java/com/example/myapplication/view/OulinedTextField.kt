package com.example.myapplication.view

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.backgroundColor
import com.example.myapplication.ui.theme.placeholderTextColor
import com.example.myapplication.ui.theme.strokeColor
import com.example.myapplication.ui.theme.textColor

@Composable
fun OutlinedTextFieldView(
    placeholderText: String,
    data: MutableState<String>,
    topPadding: Dp,
    textDecoration: TextDecoration,
    visualTransformation: VisualTransformation
) {
    OutlinedTextField(
        value = data.value,
        onValueChange = { data.value = it },
        modifier = Modifier
            .padding(top = topPadding)
            .fillMaxWidth(),
        singleLine = true,
        placeholder = { Text(text = placeholderText, fontWeight = FontWeight(400)) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = strokeColor,
            unfocusedBorderColor = strokeColor,
            textColor = textColor,
            backgroundColor = backgroundColor,
            placeholderColor = placeholderTextColor
        ),
        visualTransformation = visualTransformation,
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            textDecoration = textDecoration
        ),
        shape = RoundedCornerShape(8.dp)
    )
}
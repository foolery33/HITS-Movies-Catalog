package com.example.myapplication.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.backgroundColor
import com.example.myapplication.ui.theme.placeholderTextColor
import com.example.myapplication.ui.theme.strokeColor
import com.example.myapplication.ui.theme.textColor

@Composable
fun OutlinedPasswordFieldView(
    placeholderText: String,
    data: MutableState<String>
) {
    OutlinedTextField(
        value = data.value,
        onValueChange = { data.value = it },
        modifier = Modifier
            .padding(top = 16.dp)
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
        textStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight(400)),
        shape = RoundedCornerShape(8.dp),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
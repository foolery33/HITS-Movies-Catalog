package com.example.myapplication.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.*

@Composable
fun SexPicker(
    maleSexData: MutableState<Boolean>,
    femaleSexData: MutableState<Boolean>,
    topPadding: Dp
) {
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = topPadding)) {
        OutlinedButton(
            onClick = {
                maleSexData.value = !maleSexData.value
                if (femaleSexData.value) {
                    femaleSexData.value = !femaleSexData.value
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.5f),
            content = {
                Text(
                    "Мужчина",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }, colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = if (maleSexData.value) logInButtonColor else backgroundColor,
                contentColor = if (maleSexData.value) baseWhite else placeholderTextColor
            ),
            border = BorderStroke(1.dp, strokeColor),
            shape = sexButtonMale
        )
        OutlinedButton(
            onClick = {
                femaleSexData.value = !femaleSexData.value
                if (maleSexData.value) {
                    maleSexData.value = !maleSexData.value
                }
            },
            modifier = Modifier.fillMaxWidth(1f),
            content = {
                Text(
                    "Женщина",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }, colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = if (femaleSexData.value) logInButtonColor else backgroundColor,
                contentColor = if (femaleSexData.value) baseWhite else placeholderTextColor
            ),
            border = BorderStroke(1.dp, strokeColor),
            shape = sexButtonFemale
        )
    }
}
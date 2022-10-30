package com.example.myapplication.view

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.*

@Composable
fun DatePickerView(mDate: MutableState<String>, mDatePickerDialog: DatePickerDialog, topPadding: Dp) {
    OutlinedTextField(
        enabled = false,
        value = mDate.value.replace('/', '.'),
        onValueChange = { mDate.value },
        modifier = Modifier
            .padding(top = topPadding)
            .fillMaxWidth()
            .clickable(indication = null, interactionSource = remember {
                MutableInteractionSource()
            }) {
                mDatePickerDialog.show()
            },
        singleLine = true,
        placeholder = {
            Text(
                text = "Дата рождения",
                fontWeight = FontWeight(400)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = strokeColor,
            unfocusedBorderColor = strokeColor,
            disabledBorderColor = strokeColor,
            disabledPlaceholderColor = placeholderTextColor,
            backgroundColor = backgroundColor,
            placeholderColor = placeholderTextColor,
            disabledTextColor = textColor
        ),
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(400)
        ),
        shape = RoundedCornerShape(8.dp),
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.calendar),
                contentDescription = "calendar",
                modifier = Modifier.padding(end = 18.dp),
                tint = strokeColor
            )
        }
    )
}
package com.example.myapplication.view

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.*

@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp, backgroundColor = bottomBarColor) {
        BottomNavigationItem(selected = selectedIndex.value == 0, onClick = {
            selectedIndex.value = 0
        }, icon = {
            Icon(
                painter = painterResource(id = R.drawable.general_button),
                contentDescription = "",
                tint = if (selectedIndex.value == 0) textColor else strokeColor
            )
        }, label = {
            Text(
                "Главное", fontSize = movieInfoFontSize, fontWeight = FontWeight(
                    buttonFontWeight
                ), color = if (selectedIndex.value == 0) textColor else strokeColor
            )
        })

        BottomNavigationItem(selected = selectedIndex.value == 1, onClick = {
            selectedIndex.value = 1
        }, icon = {
            Icon(
                painter = painterResource(id = R.drawable.profile_button),
                contentDescription = "",
                tint = if (selectedIndex.value == 1) textColor else strokeColor
            )
        }, label = {
            Text(
                "Профиль", fontSize = movieInfoFontSize, fontWeight = FontWeight(
                    buttonFontWeight
                ), color = if (selectedIndex.value == 1) textColor else strokeColor
            )
        })
    }
}
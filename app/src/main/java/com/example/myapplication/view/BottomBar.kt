package com.example.myapplication.view

import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.screen.destinations.MainScreenDestination
import com.example.myapplication.screen.destinations.ProfileScreenDestination
import com.example.myapplication.ui.theme.*
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun BottomBar(navigator: DestinationsNavigator, selectedIndex: Int) {
    //val selectedIndex = remember { mutableStateOf(0) }
    Log.i("screenInfo2", selectedIndex.toString())
    BottomNavigation(elevation = 10.dp, backgroundColor = bottomBarColor) {
        BottomNavigationItem(selected = selectedIndex == 0, onClick = {
            if(selectedIndex == 1) {
                navigator.navigate(MainScreenDestination)
            }
        }, icon = {
            Icon(
                painter = painterResource(id = R.drawable.general_button),
                contentDescription = "",
                tint = if (selectedIndex == 0) textColor else strokeColor
            )
        }, label = {
            Text(
                "Главное", fontSize = movieInfoFontSize, fontWeight = FontWeight(
                    buttonFontWeight
                ), color = if (selectedIndex == 0) textColor else strokeColor
            )
        })

        BottomNavigationItem(selected = selectedIndex == 1, onClick = {
            if(selectedIndex == 0) {
                navigator.navigate(ProfileScreenDestination)
            }
        }, icon = {
            Icon(
                painter = painterResource(id = R.drawable.profile_button),
                contentDescription = "",
                tint = if (selectedIndex == 1) textColor else strokeColor
            )
        }, label = {
            Text(
                "Профиль", fontSize = movieInfoFontSize, fontWeight = FontWeight(
                    buttonFontWeight
                ), color = if (selectedIndex == 1) textColor else strokeColor
            )
        })
    }
}
package com.example.myapplication.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.domain.ViewModel
import com.example.myapplication.screen.destinations.MainScreenDestination
import com.example.myapplication.screen.destinations.ProfileScreenDestination
import com.example.myapplication.ui.theme.*
import com.example.myapplication.viewmodel.profile_screen.rememberProfileScreenState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val bottomBarHeight = 67.dp

@Composable
fun BottomBar(navigator: DestinationsNavigator, selectedIndex: Int) {

    val context = LocalContext.current
    //val profileScreenState = rememberProfileScreenState()

    BottomNavigation(modifier = Modifier.height(bottomBarHeight), backgroundColor = bottomBarColor) {
        BottomNavigationItem(selected = selectedIndex == 0, onClick = {
            if(selectedIndex == 1) {
                navigator.navigate(MainScreenDestination)
            }
        }, icon = {
            Icon(
                painter = painterResource(id = R.drawable.general_button),
                contentDescription = "",
                modifier = Modifier.padding(bottom = 7.dp),
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
                CoroutineScope(Dispatchers.Main).launch {
                    ViewModel.profileScreen.onClickProfile(context = context, navigator)
                }
                navigator.navigate(ProfileScreenDestination)
            }
        }, icon = {
            Icon(
                painter = painterResource(id = R.drawable.profile_button),
                contentDescription = "",
                modifier = Modifier.padding(bottom = 7.dp),
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
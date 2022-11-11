package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myapplication.data.Repositories
import com.example.myapplication.screen.NavGraphs
import com.example.myapplication.screen.destinations.MainScreenDestination
import com.example.myapplication.screen.destinations.SignInScreenDestination
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            MyApplicationTheme {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    startRoute = if (Repositories.authRepository.getUserToken(
                            LocalContext.current
                        ).token.isNotEmpty()
                    ) MainScreenDestination else SignInScreenDestination
                )
            }
        }
    }
}

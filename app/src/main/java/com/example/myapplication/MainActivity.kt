package com.example.myapplication

import android.graphics.Movie
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myapplication.screen.MovieScreen
import com.example.myapplication.screen.ProfileScreen
import com.example.myapplication.screen.main_screen.MainScreen
import com.example.myapplication.screen.sign_in_screen.SignInScreen
import com.example.myapplication.screen.sign_up_screen.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            ProfileScreen()
        }
    }
}

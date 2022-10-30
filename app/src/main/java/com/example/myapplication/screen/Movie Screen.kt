package com.example.myapplication.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.ui.theme.backgroundColor
import com.example.myapplication.view.TopBar
import com.example.myapplication.view.TopBarNew

@Composable
fun MovieScreen() {
    val lazyListState = rememberLazyListState()
    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor), horizontalAlignment = Alignment.CenterHorizontally) {
                items(100) {
                    Text("ADASD", color = Color.White)
                }
            }
            TopBarNew(lazyListState)
        }
    }
}
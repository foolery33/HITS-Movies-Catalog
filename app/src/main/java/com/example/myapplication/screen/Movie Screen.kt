package com.example.myapplication.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.backgroundColor
import com.example.myapplication.view.Body
import com.example.myapplication.view.CollapsingToolbarParallaxEffect
import com.example.myapplication.view.Header

@Composable
fun MovieScreen() {
    val headerHeight = 275.dp
    val lazyListState = rememberLazyListState()
    val scroll = rememberScrollState()
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }

    Scaffold {
        CollapsingToolbarParallaxEffect()
    }
    LazyColumn {

    }
}
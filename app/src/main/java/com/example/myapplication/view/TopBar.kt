package com.example.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.backgroundColor
import com.example.myapplication.ui.theme.defaultPadding
import com.example.myapplication.ui.theme.sectionButtonFontWeight

@Preview
@Composable
fun TopBar() {
    val scrollState = rememberScrollState()
    Box {
        Box(
            modifier = Modifier
                .alpha(
                    kotlin.math.max(
                        0.3f,
                        scrollState.value / scrollState.maxValue.toFloat()
                    )
                )
                .fillMaxWidth()
                .height(56.dp)
                .background(backgroundColor)
        )
        Image(
            painter = painterResource(id = R.drawable.movie_screen_poster),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Icon(
            modifier = Modifier
                .padding(20.dp),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "",
            tint = Color.White
        )
        Box(modifier = Modifier.matchParentSize().padding(defaultPadding), contentAlignment = Alignment.BottomStart) {
            Text(
                text = "Побег из Шоушенка",
                color = Color.White,
                fontSize = 39.sp,
                fontWeight = FontWeight(
                    sectionButtonFontWeight
                )
            )
        }
    }
}
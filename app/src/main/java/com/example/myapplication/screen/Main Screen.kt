package com.example.myapplication.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.backgroundColor
import com.example.myapplication.ui.theme.defaultTopPadding
import com.example.myapplication.ui.theme.doubleDefaultTopPadding
import com.example.myapplication.ui.theme.logInButtonColor
import com.example.myapplication.view.ButtonView
import com.example.myapplication.view.SectionText
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun MainScreen() {

    var sizeImage by remember { mutableStateOf(IntSize.Zero) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = backgroundColor)
        .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.magicians_poster),
                contentDescription = "",
                modifier = Modifier
                    .onGloballyPositioned { sizeImage = it.size }
                    .fillMaxWidth(), contentScale = ContentScale.Crop)
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            ),
                            startY = sizeImage.height.toFloat() / 1.4f,
                            endY = sizeImage.height.toFloat()
                        )
                    )
            )
            Box(modifier = Modifier
                .matchParentSize()
                .padding(bottom = 32.dp), contentAlignment = Alignment.BottomCenter) {
                ButtonView(buttonText = "Смотреть", paddingValues = PaddingValues(horizontal = 108.dp), backgroundColor = logInButtonColor, textColor = Color.White)
            }
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            SectionText(text = "Избранное", paddingValues = doubleDefaultTopPadding)
            LazyRow(modifier = Modifier.horizontalScroll(rememberScrollState())) {

            }
        }
    }
}
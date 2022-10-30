package com.example.myapplication.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.backgroundColor
import com.example.myapplication.ui.theme.defaultPadding
import com.example.myapplication.ui.theme.sectionButtonFontWeight
import kotlin.math.max

@Composable
fun TopBarNew(lazyListState: LazyListState) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = 1000))
            .height(height = if (lazyListState.isScrolled) 0.dp else 1.dp),
    ) {
        Box {
            Box(
                modifier = Modifier
                    /*.alpha(
                        max(
                            0.3f,
                            lazyListState./ scrollState . maxValue . toFloat ()
                        )
                    )*/
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
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(defaultPadding),
                contentAlignment = Alignment.BottomStart
            ) {
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
}

val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0
package com.example.myapplication.viewmodel.movie_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class ReviewDialogState {
    val showDialog = mutableStateOf(false)
    val textData = mutableStateOf("")
    val ratingValue = mutableStateOf(0)
    val checkedState = mutableStateOf(false)
}

@Composable
fun rememberReviewDialogState() = remember { ReviewDialogState() }
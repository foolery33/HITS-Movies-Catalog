package com.example.myapplication.domain.main_screen.use_cases

import androidx.compose.foundation.lazy.LazyListState

class GetFirstVisibleIndexUseCase {

    fun getIndex(state: LazyListState): Int {
        val layoutInfo = state.layoutInfo
        val visibleItemsInfo = layoutInfo.visibleItemsInfo
        val fullyVisibleItemsInfo = visibleItemsInfo.toMutableList()

        val lastItem = fullyVisibleItemsInfo.last()

        val viewportHeight = layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset

        if (lastItem.offset + lastItem.size > viewportHeight) {
            fullyVisibleItemsInfo.removeLast()
        }

        val firstItemIfLeft = fullyVisibleItemsInfo.firstOrNull()
        if (firstItemIfLeft != null && firstItemIfLeft.offset < layoutInfo.viewportStartOffset) {
            fullyVisibleItemsInfo.removeFirst()
        }
        fullyVisibleItemsInfo.map { it.index }

        return fullyVisibleItemsInfo.first().index
    }

}
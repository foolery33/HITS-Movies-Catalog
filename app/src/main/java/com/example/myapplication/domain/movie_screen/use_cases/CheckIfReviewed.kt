package com.example.myapplication.domain.movie_screen.use_cases

import com.example.myapplication.network.movie.ReviewModel

class CheckIfReviewed {

    fun result(nickname: String, reviewList: List<ReviewModel>): Boolean {
        for (i in reviewList.indices) {
            if(reviewList[i].author?.nickName == nickname) {
                return true
            }
        }
        return false
    }

}
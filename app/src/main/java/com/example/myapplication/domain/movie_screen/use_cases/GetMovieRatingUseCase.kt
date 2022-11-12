package com.example.myapplication.domain.movie_screen.use_cases

import com.example.myapplication.network.movie.ReviewShortModel
import java.math.RoundingMode

class GetMovieRatingUseCase {

    fun getRating(reviews: List<ReviewShortModel>): Double {
        var marksSum = 0.0
        for (i in reviews.indices) {
            marksSum += reviews[i].rating
        }
        return (marksSum / reviews.size).toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
    }

}
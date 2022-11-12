package com.example.myapplication.domain.movie_screen.use_cases

import com.example.myapplication.domain.ViewModels

class GetFavouriteStatusUseCase {

    fun isFavourite(id: String) {

        var flag = false

        for (i in ViewModels.mainScreen.favouriteMovies.movies.indices) {
            if (ViewModels.mainScreen.favouriteMovies.movies[i].id == id) {
                ViewModels.movieScreen.isFavourite.value = true
                flag = true
                break
            }
        }
        if(!flag) {
            ViewModels.movieScreen.isFavourite.value = false
        }
    }

}
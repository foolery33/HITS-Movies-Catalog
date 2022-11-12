package com.example.myapplication.domain.main_screen.use_cases

import com.example.myapplication.network.favourite_movies.GenreModel

class ConvertMovieListUseCase {

    fun toString(genres: List<GenreModel>?): String {

        var genresString = ""

        for (element in genres!!) {
            if(element.name != null) {
                genresString += "${element.name}, "
            }
        }

        return genresString.substring(0, genresString.length - 2)
    }

}
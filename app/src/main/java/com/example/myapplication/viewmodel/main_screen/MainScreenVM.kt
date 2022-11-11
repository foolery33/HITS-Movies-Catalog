package com.example.myapplication.viewmodel.main_screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.domain.ViewModels
import com.example.myapplication.domain.main_screen.use_cases.DeleteMovieFromFavouritesUseCase
import com.example.myapplication.domain.main_screen.use_cases.GetFavouritesUseCase
import com.example.myapplication.domain.movie_screen.use_cases.GetFavouriteStatusUseCase
import com.example.myapplication.domain.movie_screen.use_cases.GetMovieDetailsUseCase
import com.example.myapplication.network.favourite_movies.FavouriteMovieList
import com.example.myapplication.network.movie.MovieDetailsModel
import com.example.myapplication.network.movie.MovieElementModel
import com.example.myapplication.network.movie.MoviePageSource
import com.example.myapplication.screen.destinations.MovieScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.Flow

class MainScreenState: ViewModel() {

    lateinit var favouriteMovies: FavouriteMovieList
    var isFavourites = mutableStateOf(false)

    lateinit var lazyListState: LazyListState

    var movies: MutableMap<String, MovieDetailsModel> = mutableMapOf()

    val pagingMovie: Flow<PagingData<MovieElementModel>> = Pager(PagingConfig(pageSize = 6)) {
        MoviePageSource()
    }.flow.cachedIn(viewModelScope)


    suspend fun getFavourites(
        context: Context
    ) {
        try {
            favouriteMovies = GetFavouritesUseCase().getFavourites(context = context)
            isFavourites.value = favouriteMovies.movies.isNotEmpty()
        } catch (e: Exception) {
            Log.i("favourites", e.message.toString())
        }
    }

    suspend fun onClickMovie(id: String, navigator: DestinationsNavigator) {
        try {
            val response: MovieDetailsModel = GetMovieDetailsUseCase().getDetails(id)
            GetFavouriteStatusUseCase().isFavourite(id)
            movies[response.id] = response
            navigator.navigate(MovieScreenDestination(groupName = id))
        } catch (e: Exception) {
            Log.i("errorList", e.message.toString())
        }
    }

    suspend fun onClickDelete(context: Context, id: String) {
        try {
            DeleteMovieFromFavouritesUseCase().deleteMovie(context = context, id = id)
            ViewModels.movieScreen.isFavourite.value = false
        } catch(e: Exception) {
            Log.i("errorList", "some delete movie error")
        }
    }

}
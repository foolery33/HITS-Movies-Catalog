package com.example.myapplication.viewmodel.main_screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.MutableState
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
import com.example.myapplication.domain.main_screen.use_cases.GetMoviesPageUseCase
import com.example.myapplication.domain.movie_screen.use_cases.GetFavouriteStatusUseCase
import com.example.myapplication.domain.movie_screen.use_cases.GetMovieDetailsUseCase
import com.example.myapplication.network.favourite_movies.FavouriteMovieList
import com.example.myapplication.network.favourite_movies.MovieModel
import com.example.myapplication.network.movie.MovieDetailsModel
import com.example.myapplication.network.movie.MovieElementModel
import com.example.myapplication.network.movie.MoviePageSource
import com.example.myapplication.screen.destinations.MovieScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.Flow

class MainScreenState: ViewModel() {

    lateinit var favouriteMovies: FavouriteMovieList

    var favouriteMoviesList: MutableState<List<MovieModel> > = mutableStateOf(listOf())
    var isFavourites = mutableStateOf(false)

    var movieList: List<MovieElementModel> = listOf()
    var promotedMovie = ""

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
            favouriteMoviesList.value = favouriteMovies.movies
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
            ViewModels.movieScreen.reviewList.value = response.reviews
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

    suspend fun getMoviesByPage(page: Int) {
        movieList = GetMoviesPageUseCase().getPage(page).movies!!
    }

    fun getPromotedMovie() {
        try {
            this.promotedMovie = if(movieList.isNotEmpty()) movieList[0].poster!! else ""
        } catch (e: Exception) {
            Log.i("errorList", "failed to load promoted movie")
        }
    }

}
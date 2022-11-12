package com.example.myapplication.network.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.network.Network
import okio.IOException
import retrofit2.Retrofit

class MoviePageSource: PagingSource<Int, MovieElementModel>() {

    val api: MovieApi = Network.getMovieApi()

    override fun getRefreshKey(state: PagingState<Int, MovieElementModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieElementModel> {
        return try {
            val nextPage = params.key ?: 1
            val movieList = api.getMoviePage(page = nextPage)
            LoadResult.Page(
                data = movieList.movies!!,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (movieList.movies.isEmpty()) null else movieList.pageInfo.currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}
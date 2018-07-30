package com.example.arildojunior.upcomingmovies.data.repository

import android.arch.paging.PagedList
import android.util.Log
import com.example.arildojunior.upcomingmovies.data.api.APIDataFactory
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB
import com.example.arildojunior.upcomingmovies.data.room.RoomDataFactory
import com.example.arildojunior.upcomingmovies.data.room.model.GenreDB
import io.reactivex.schedulers.Schedulers

class PageListMovieCallback(private val apiDataFactory: APIDataFactory,
                            private val roomDataFactory: RoomDataFactory) : PagedList.BoundaryCallback<MovieDB>() {

    private var isRequestRunning = false
    private var requestedPage = 1

    override fun onZeroItemsLoaded() {
        fetchAndStoreMovies()
    }

    override fun onItemAtEndLoaded(itemAtEnd: MovieDB) {
        fetchAndStoreMovies()
    }

    private fun fetchAndStoreMovies() {
        if (isRequestRunning) return

        isRequestRunning = true
        fetchMoviesGenres()
    }

    private fun fetchMoviesGenres () {
        apiDataFactory.fetchMoviesGenres()
                .map { genreApiList -> genreApiList.map { it.toGenreEntity() } }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ moviesGenres : List<GenreDB> ->
                    roomDataFactory.storeGenres(moviesGenres)
                    apiDataFactory.fetchMovies(requestedPage)
                            .map { movieApiList -> movieApiList.map {
                                val genres : List<GenreDB> = roomDataFactory.getGenres(it.genreIds)
                                it.toMovieEntity(getGenreNames(genres))
                            } }
                            .doOnSuccess { listMovie ->
                                if (listMovie.isNotEmpty()) {
                                    roomDataFactory.storeMovies(listMovie)
                                }
                                requestedPage++
                            }
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .toCompletable()
                            .doFinally { isRequestRunning = false }
                            .subscribe({ Log.i("PageListMovieCallback", "Movies Loaded") })
                })
    }

    private fun getGenreNames (genres : List<GenreDB>) : List<String> {
        val genreNames : MutableList<String> = mutableListOf<String>()
        for (item: GenreDB in genres) {
            genreNames.add(item.name)
        }
        return genreNames
    }
}

package com.example.arildojunior.upcomingmovies.data.repository

import android.arch.paging.PagedList
import android.util.Log
import com.example.arildojunior.upcomingmovies.data.api.APIDataFactory
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB
import com.example.arildojunior.upcomingmovies.data.room.RoomDataFactory
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
        apiDataFactory.fetchMovies(requestedPage)
                .map { movieApiList -> movieApiList.map { it.toMovieEntity() } }
                .doOnSuccess { listMovie ->
                    if (listMovie.isNotEmpty()) {
                        roomDataFactory.storeMovies(listMovie)
                    } else {
                    }
                    requestedPage++
                }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .toCompletable()
                .doFinally { isRequestRunning = false }
                .subscribe({ Log.i("PageListMovieCallback", "Movies Completed") })

    }
}

package com.example.arildojunior.upcomingmovies.data.repository

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.example.arildojunior.upcomingmovies.data.api.APIDataFactory
import com.example.arildojunior.upcomingmovies.data.api.model.Genre
import com.example.arildojunior.upcomingmovies.data.room.DATABASE.PAGE_SIZE
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB
import com.example.arildojunior.upcomingmovies.data.room.RoomDataFactory
import io.reactivex.Observable

class MoviesRepository(private val apiDataFactory: APIDataFactory, private val roomFactory: RoomDataFactory) {

    fun fetchUpcomingMovies() : Observable<PagedList<MovieDB>> = RxPagedListBuilder(roomFactory.getMovies(), PAGE_SIZE)
            .setBoundaryCallback(PageListMovieCallback(apiDataFactory, roomFactory))
            .buildObservable()

    fun fetchMoviesGenres(): Observable<List<Genre>> {
        return apiDataFactory.fetchMoviesGenres()
    }

}

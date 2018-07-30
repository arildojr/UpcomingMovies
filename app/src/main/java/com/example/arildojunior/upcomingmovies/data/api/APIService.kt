package com.example.arildojunior.upcomingmovies.data.api

import com.example.arildojunior.upcomingmovies.data.api.model.Genre
import com.example.arildojunior.upcomingmovies.data.api.model.Movie
import com.example.arildojunior.upcomingmovies.utils.Constants
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET(Constants.GET_UPCOMING)
    fun getUpcomingMovies(@Query(Constants.PAGE_QUERY) key: Int, @Query(Constants.API_KEY_QUERY) apiKey: String = Constants.API_KEY): Single<List<Movie>>

    @GET(Constants.GET_GENRES)
    fun getMoviesGenres(@Query(Constants.API_KEY_QUERY) apiKey: String = Constants.API_KEY): Observable<List<Genre>>
}

package com.example.arildojunior.upcomingmovies.api

import com.example.arildojunior.upcomingmovies.utils.Constants
import io.reactivex.Observable
import com.example.arildojunior.upcomingmovies.model.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("upcoming?api_key=" + Constants.API_KEY)
    fun getUpcomingMovies(@Query("page") key: Int): Observable<MoviesList>
}

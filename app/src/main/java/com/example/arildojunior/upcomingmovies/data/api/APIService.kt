package com.example.arildojunior.upcomingmovies.data.api

import com.example.arildojunior.upcomingmovies.data.api.model.Movie
import com.example.arildojunior.upcomingmovies.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("upcoming?api_key=" + Constants.API_KEY)
    fun getUpcomingMovies(@Query("page") key: Int): Single<List<Movie>>
}

package com.example.arildojunior.upcomingmovies.data.api

class APIDataFactory(private val apiService: APIService) {

    fun fetchMovies(page: Int) = apiService.getUpcomingMovies(page)
}

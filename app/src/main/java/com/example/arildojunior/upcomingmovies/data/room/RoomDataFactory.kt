package com.example.arildojunior.upcomingmovies.data.room

import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB

class RoomDataFactory(private val moviesDao: MoviesDao) {

    fun storeMovies(movieDBList: List<MovieDB>) = moviesDao.insert(movieDBList)

    fun getMovies() = moviesDao.allMovies()
}

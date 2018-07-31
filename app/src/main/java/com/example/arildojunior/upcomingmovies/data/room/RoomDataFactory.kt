package com.example.arildojunior.upcomingmovies.data.room

import com.example.arildojunior.upcomingmovies.data.room.model.GenreDB
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB

class RoomDataFactory(private val moviesDao: MoviesDao) {

    fun storeMovies(movieDBList: List<MovieDB>) = moviesDao.insert(movieDBList)

    fun storeGenres(genreDBList: List<GenreDB>) = moviesDao.insertGenres(genreDBList)

    fun getMovies() = moviesDao.allMovies()

    fun getGenres(genres : List<Int>) = moviesDao.allGenres(genres)
}

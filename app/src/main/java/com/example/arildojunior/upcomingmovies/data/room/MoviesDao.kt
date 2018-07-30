package com.example.arildojunior.upcomingmovies.data.room

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.arildojunior.upcomingmovies.data.api.model.Genre
import com.example.arildojunior.upcomingmovies.data.room.DATABASE.SELECT_GENRES
import com.example.arildojunior.upcomingmovies.data.room.DATABASE.SELECT_MOVIE
import com.example.arildojunior.upcomingmovies.data.room.model.GenreDB
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieDBList: List<MovieDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genreDBList: List<GenreDB>)

    @Query(SELECT_MOVIE)
    fun allMovies(): DataSource.Factory<Int, MovieDB>

    @Query(SELECT_GENRES)
    fun allGenres(genreIds : List<Int>): List<GenreDB>
}

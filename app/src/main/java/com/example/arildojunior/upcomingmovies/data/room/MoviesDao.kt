package com.example.arildojunior.upcomingmovies.data.room

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.arildojunior.upcomingmovies.data.room.DATABASE.SELECT_MOVIE
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieDBList: List<MovieDB>)

    @Query(SELECT_MOVIE)
    fun allMovies(): DataSource.Factory<Int, MovieDB>
}

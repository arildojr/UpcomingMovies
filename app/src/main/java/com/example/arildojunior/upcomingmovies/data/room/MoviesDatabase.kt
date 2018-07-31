package com.example.arildojunior.upcomingmovies.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.arildojunior.upcomingmovies.data.room.DATABASE.DATABASE_MOVIE
import com.example.arildojunior.upcomingmovies.data.room.DATABASE.DATABASE_MOVIE_VERSION
import com.example.arildojunior.upcomingmovies.data.room.model.GenreDB
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB

@Database(entities = [MovieDB::class, GenreDB::class], version = DATABASE_MOVIE_VERSION, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun movieDao(): MoviesDao

    companion object {

        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase = INSTANCE
                ?: synchronized(this) {
            INSTANCE
                    ?: buildMoviesDatabase(context).also { INSTANCE = it }
        }

        private fun buildMoviesDatabase(context: Context) = Room.databaseBuilder(context, MoviesDatabase::class.java, DATABASE_MOVIE).build()
    }
}
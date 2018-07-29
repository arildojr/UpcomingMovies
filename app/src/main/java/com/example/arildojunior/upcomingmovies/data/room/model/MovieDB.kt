package com.example.arildojunior.upcomingmovies.data.room.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.arildojunior.upcomingmovies.data.room.DATABASE.TABLE_MOVIE

@Entity(tableName = TABLE_MOVIE)
data class MovieDB(@PrimaryKey val id: Long,
                   val voteCount: Int,
                   val video: Boolean,
                   val voteAverage: Float,
                   val title: String,
                   val popularity: Float,
                   val posterPath: String,
                   val originalLanguage: String,
                   val originalTitle: String,
                   val genreIds: List<Int>,
                   val backdropPath: String,
                   val adult: Boolean,
                   val overview: String,
                   val releaseDate: String)


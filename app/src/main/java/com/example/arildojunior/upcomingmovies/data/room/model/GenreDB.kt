package com.example.arildojunior.upcomingmovies.data.room.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.arildojunior.upcomingmovies.data.api.model.Genre
import com.example.arildojunior.upcomingmovies.data.room.DATABASE.TABLE_GENRES
import com.example.arildojunior.upcomingmovies.data.room.DATABASE.TABLE_MOVIE

@Entity(tableName = TABLE_GENRES)
data class GenreDB(@PrimaryKey val id: Long,
                   val name: String)


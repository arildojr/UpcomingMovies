package com.example.arildojunior.upcomingmovies.data.room

object DATABASE {
    const val DATABASE_MOVIE = "movies.db"
    const val DATABASE_MOVIE_VERSION = 1
    const val TABLE_MOVIE = "movie"
    const val TABLE_GENRES = "genres"
    const val SELECT_MOVIE = "SELECT * FROM $TABLE_MOVIE ORDER BY popularity DESC, title ASC"
    const val SELECT_GENRES = "SELECT * FROM $TABLE_GENRES WHERE id IN (:genreIds)"
    const val PAGE_SIZE = 20
}

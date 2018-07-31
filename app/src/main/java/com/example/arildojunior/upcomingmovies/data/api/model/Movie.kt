package com.example.arildojunior.upcomingmovies.data.api.model

import com.example.arildojunior.upcomingmovies.data.api.APIPath
import com.example.arildojunior.upcomingmovies.data.api.ImageSizes
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Movie(@SerializedName("vote_count") val voteCount: Int,
                 @SerializedName("id") val id: Long,
                 @SerializedName("video") val video: Boolean,
                 @SerializedName("vote_average") val voteAverage: Float,
                 @SerializedName("title") val title: String,
                 @SerializedName("popularity") val popularity: Float,
                 @SerializedName("poster_path") val posterPath: String,
                 @SerializedName("original_language") val originalLanguage: String,
                 @SerializedName("original_title") val originalTitle: String,
                 @SerializedName("genre_ids") val genreIds: List<Int>,
                 @SerializedName("backdrop_path") val backdropPath: String,
                 @SerializedName("adult") val adult: Boolean,
                 @SerializedName("overview") val overview: String,
                 @SerializedName("release_date") val releaseDate: String) : Serializable {

    fun toMovieEntity(genreNames : List<String>) = this.run {
        MovieDB(id, voteCount, video, voteAverage, title, popularity,
                APIPath.getImagePath(ImageSizes.W154, posterPath), originalLanguage, originalTitle,
                genreIds, genreNames, APIPath.getImagePath(ImageSizes.W780, backdropPath), adult, overview, releaseDate)
    }
}

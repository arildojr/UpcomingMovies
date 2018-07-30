package com.example.arildojunior.upcomingmovies.data.api.model

import com.example.arildojunior.upcomingmovies.data.api.APIPath
import com.example.arildojunior.upcomingmovies.data.api.ImageSizes
import com.example.arildojunior.upcomingmovies.data.room.model.GenreDB
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Genre (@SerializedName("id") val id: Long,
                  @SerializedName("name") val name: String) : Serializable {
    fun toGenreEntity() = this.run {
        GenreDB(id, name)
    }
}
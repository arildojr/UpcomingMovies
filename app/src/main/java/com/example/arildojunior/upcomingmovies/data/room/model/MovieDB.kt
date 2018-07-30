package com.example.arildojunior.upcomingmovies.data.room.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
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
                   val genreNames: List<String>,
                   val backdropPath: String,
                   val adult: Boolean,
                   val overview: String,
                   val releaseDate: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readLong(),
            source.readInt(),
            1 == source.readInt(),
            source.readFloat(),
            source.readString(),
            source.readFloat(),
            source.readString(),
            source.readString(),
            source.readString(),
            ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) },
            source.createStringArrayList(),
            source.readString(),
            1 == source.readInt(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeInt(voteCount)
        writeInt((if (video) 1 else 0))
        writeFloat(voteAverage)
        writeString(title)
        writeFloat(popularity)
        writeString(posterPath)
        writeString(originalLanguage)
        writeString(originalTitle)
        writeList(genreIds)
        writeStringList(genreNames)
        writeString(backdropPath)
        writeInt((if (adult) 1 else 0))
        writeString(overview)
        writeString(releaseDate)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieDB> = object : Parcelable.Creator<MovieDB> {
            override fun createFromParcel(source: Parcel): MovieDB = MovieDB(source)
            override fun newArray(size: Int): Array<MovieDB?> = arrayOfNulls(size)
        }
    }
}
package com.example.arildojunior.upcomingmovies.data.room

import android.arch.persistence.room.TypeConverter
import java.util.Collections.emptyList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import android.arch.persistence.room.TypeConverters
import android.arch.persistence.room.PrimaryKey




class Converters {
    internal var gson = Gson()

    @TypeConverter
    fun toList(data: String?): MutableList<Int> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<MutableList<Int>>() {}.getType()
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromList(someObjects: MutableList<Int>): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun toGenreList(data: String?): MutableList<String> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<MutableList<String>>() {}.getType()
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromGenreList(someObjects: MutableList<String>): String {
        return gson.toJson(someObjects)
    }
}
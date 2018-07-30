package com.example.arildojunior.upcomingmovies.data.api

import com.example.arildojunior.upcomingmovies.data.api.model.Movie
import com.google.gson.*
import java.lang.reflect.Type
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class APIDeserializer<T> : JsonDeserializer<T> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): T = Gson().run {
        val moviesJsonObject = json?.asJsonObject?.get("results")?.asJsonArray
        val genresJsonObject = json?.asJsonObject?.get("genres")?.asJsonArray
        val jsonObject = if (moviesJsonObject == null) genresJsonObject else moviesJsonObject
        fromJson(jsonObject, typeOfT)
    }
}

package com.example.arildojunior.upcomingmovies.data.api

import com.google.gson.*
import java.lang.reflect.Type

class APIDeserializer<T> : JsonDeserializer<T> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): T = Gson().run {
        val moviesJsonObject = json?.asJsonObject?.get("results")?.asJsonArray
        fromJson(moviesJsonObject, typeOfT)
    }
}

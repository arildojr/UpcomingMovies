package com.example.arildojunior.upcomingmovies.data.api

import com.example.arildojunior.upcomingmovies.data.api.model.Genre
import com.example.arildojunior.upcomingmovies.data.api.model.Movie
import com.example.arildojunior.upcomingmovies.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIClient() {
    companion object {
        fun getService(): APIService = createRetrofit().create(APIService::class.java)
        private fun createRetrofit(): Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonDeserializer()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build()
        fun provideOkHttpClient(): OkHttpClient =
                OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build()
        private fun gsonDeserializer(): Gson = GsonBuilder()
                .registerTypeAdapter(object : TypeToken<List<@JvmSuppressWildcards Movie>>() {}.type, APIDeserializer<Movie>())
                .registerTypeAdapter(object : TypeToken<List<@JvmSuppressWildcards Genre>>() {}.type, APIDeserializer<Genre>())
                .setLenient()
                .create()
    }
}

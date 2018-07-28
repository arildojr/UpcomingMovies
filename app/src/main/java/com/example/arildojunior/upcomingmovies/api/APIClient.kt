package com.example.arildojunior.upcomingmovies.api

import com.example.arildojunior.upcomingmovies.utils.Constants
import io.reactivex.Observable
import com.example.arildojunior.upcomingmovies.model.MoviesList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIClient(okHttpClient: OkHttpClient) {
    private val service: APIService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        service = retrofit.create(APIService::class.java)
    }

    fun getUpcomingMoviesObservable(page: Int): Observable<MoviesList> {
        return service.getUpcomingMovies(page)
    }
}

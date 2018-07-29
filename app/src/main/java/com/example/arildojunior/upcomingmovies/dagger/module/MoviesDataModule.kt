package com.example.arildojunior.upcomingmovies.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import com.example.arildojunior.upcomingmovies.data.api.APIClient
import com.example.arildojunior.upcomingmovies.dagger.scope.AppScope
import com.example.arildojunior.upcomingmovies.data.api.APIDataFactory
import com.example.arildojunior.upcomingmovies.data.repository.MoviesRepository
import com.example.arildojunior.upcomingmovies.data.room.MoviesDatabase
import com.example.arildojunior.upcomingmovies.data.room.RoomDataFactory
import okhttp3.OkHttpClient

@Module(includes = [(APIModule::class)])
class MoviesDataModule {
    @AppScope
    @Provides
    fun provideAPIClient(okHttpClient : OkHttpClient): APIClient {
        return APIClient(okHttpClient)
    }

    @AppScope
    @Provides
    fun provideMoviesDatabase(context: Context): RoomDataFactory {
        val moviesDatabase = MoviesDatabase.getInstance(context)
        return RoomDataFactory(moviesDatabase.movieDao())
    }

    @AppScope
    @Provides
    fun provideMoviesRepository(context: Context) =
            MoviesRepository(APIDataFactory(APIClient.getService()), provideMoviesDatabase(context))
}

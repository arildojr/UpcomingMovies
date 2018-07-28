package com.example.arildojunior.upcomingmovies.dagger.module

import dagger.Module
import dagger.Provides
import com.example.arildojunior.upcomingmovies.api.APIClient
import com.example.arildojunior.upcomingmovies.dagger.scope.AppScope
import okhttp3.OkHttpClient

@Module(includes = [(APIModule::class)])
class MoviesAPIModule {
    @AppScope
    @Provides
    fun provideAPIClient(okHttpClient : OkHttpClient): APIClient {
        return APIClient(okHttpClient)
    }
}

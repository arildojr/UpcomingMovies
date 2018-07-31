package com.example.arildojunior.upcomingmovies.dagger.module

import android.content.Context
import android.util.Log
import com.example.arildojunior.upcomingmovies.dagger.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File


@Module(includes = [(ContextModule::class)])
class APIModule {

    @Provides
    @AppScope
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build()
    }

    @Provides
    @AppScope
    internal fun cache(cacheFile: File): Cache {
        cacheFile.mkdirs()
        return Cache(cacheFile, (10 * 1024 * 1024).toLong()) // 10 MB)
    }

    @Provides
    @AppScope
    internal fun cacheFile(context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }

    @Provides
    @AppScope
    internal fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("HttpLoggingInterceptor", message) })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return loggingInterceptor
    }
}

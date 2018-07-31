package com.example.arildojunior.upcomingmovies.dagger.module

import android.content.Context
import com.example.arildojunior.upcomingmovies.dagger.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    @AppScope
    fun context(): Context {
        return context
    }

}

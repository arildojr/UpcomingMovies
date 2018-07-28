package com.example.arildojunior.upcomingmovies

import android.app.Application
import com.example.arildojunior.upcomingmovies.dagger.module.ContextModule
import com.example.arildojunior.upcomingmovies.dagger.component.AppComponent
import com.example.arildojunior.upcomingmovies.dagger.component.DaggerAppComponent

class App : Application() {
    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var component: AppComponent
    }
    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
                .builder()
                .contextModule(ContextModule(this))
                .build()
    }

}
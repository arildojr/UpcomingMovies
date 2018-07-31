package com.example.arildojunior.upcomingmovies.dagger.component

import dagger.Component
import com.example.arildojunior.upcomingmovies.App
import com.example.arildojunior.upcomingmovies.dagger.scope.AppScope
import com.example.arildojunior.upcomingmovies.dagger.module.ViewModuleFactoryModule
import com.example.arildojunior.upcomingmovies.view.MoviesFragment

@AppScope
@Component(modules = [ViewModuleFactoryModule::class])
interface AppComponent {
    fun inject(myApplication: App)

    fun inject(moviesFragment: MoviesFragment)
}

package com.example.arildojunior.upcomingmovies.dagger.module

import dagger.Module
import dagger.Provides
import com.example.arildojunior.upcomingmovies.data.api.APIClient
import com.example.arildojunior.upcomingmovies.dagger.scope.AppScope
import com.example.arildojunior.upcomingmovies.data.repository.MoviesRepository
import com.example.arildojunior.upcomingmovies.viewmodel.ViewModelFactory

@Module(includes = [(MoviesDataModule::class)])
class ViewModuleFactoryModule {
    @AppScope
    @Provides
    fun provideViewModuleFactory(moviesRepository: MoviesRepository): ViewModelFactory {
        return ViewModelFactory(moviesRepository)
    }
}

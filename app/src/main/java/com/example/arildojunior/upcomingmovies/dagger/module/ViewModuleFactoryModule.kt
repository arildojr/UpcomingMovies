package com.example.arildojunior.upcomingmovies.dagger.module

import dagger.Module
import dagger.Provides
import com.example.arildojunior.upcomingmovies.api.APIClient
import com.example.arildojunior.upcomingmovies.dagger.scope.AppScope
import com.example.arildojunior.upcomingmovies.viewmodel.ViewModelFactory

@Module(includes = [(MoviesAPIModule::class)])
class ViewModuleFactoryModule {
    @AppScope
    @Provides
    fun provideViewModuleFactory(apiClient: APIClient): ViewModelFactory {
        return ViewModelFactory(apiClient)
    }
}

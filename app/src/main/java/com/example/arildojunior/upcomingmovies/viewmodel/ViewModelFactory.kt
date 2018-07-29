package com.example.arildojunior.upcomingmovies.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.arildojunior.upcomingmovies.data.api.APIClient
import com.example.arildojunior.upcomingmovies.data.api.APIDataFactory
import com.example.arildojunior.upcomingmovies.data.repository.MoviesRepository

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val moviesRepository: MoviesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(moviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.arildojunior.upcomingmovies.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.arildojunior.upcomingmovies.api.APIClient

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val apiClient: APIClient) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(apiClient) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.arildojunior.upcomingmovies.model

import com.google.gson.annotations.SerializedName

data class MoviesList(@SerializedName("results") val upcomingMovies : List<Movie>) {
}
package com.example.arildojunior.upcomingmovies.api

import com.example.arildojunior.upcomingmovies.utils.Constants

enum class ImageSizes(val sizeName: String) {
    W92("w92"), W154("w154"), W185("w185"), W342("w342"),
    W500("w500"), W780("w780"), ORIGINAL("original")
}

class APIPath {
    companion object {
        fun getImagePath(size: ImageSizes, path: String): String {
            return Constants.BASE_URL_IMAGES + size.sizeName + path;
        }
    }
}
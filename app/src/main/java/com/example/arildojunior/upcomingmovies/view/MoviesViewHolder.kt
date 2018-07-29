package com.example.arildojunior.upcomingmovies.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(movie: MovieDB) = itemView.run {
        if (movie.posterPath.length > 0) {
            Glide.with(poster.context).load(movie.posterPath).into(poster)
        }
        title.text = movie.title
        release_date.text = movie.releaseDate
        genre.text = "Genre"
    }

    fun clear() = itemView.run {
        poster.invalidate()
        title.invalidate()
        release_date.invalidate()
        genre.invalidate()
    }
}

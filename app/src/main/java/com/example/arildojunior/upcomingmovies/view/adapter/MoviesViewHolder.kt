package com.example.arildojunior.upcomingmovies.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB
import com.example.arildojunior.upcomingmovies.extension.removeBrackets
import kotlinx.android.synthetic.main.item_movie.view.*
import com.example.arildojunior.upcomingmovies.data.bus.RxBus
import com.example.arildojunior.upcomingmovies.view.MovieDetailsActivity


class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(movie: MovieDB) = itemView.run {
        if (movie.posterPath.length > 0) {
            Glide.with(poster.context).load(movie.posterPath).into(poster)
        }
        title.text = movie.title
        release_date.text = movie.releaseDate
        genre.text = movie.genreNames.toString().removeBrackets()
        setOnClickListener(View.OnClickListener {
            val intent = MovieDetailsActivity.newIntent(context)
            RxBus.getSubject().onNext(movie)
            context.startActivity(intent)
        })
    }

    fun clear() = itemView.run {
        poster.invalidate()
        title.invalidate()
        release_date.invalidate()
        genre.invalidate()
    }
}

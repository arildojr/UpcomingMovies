package com.example.arildojunior.upcomingmovies.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.arildojunior.upcomingmovies.utils.Constants
import com.example.arildojunior.upcomingmovies.R
import com.example.arildojunior.upcomingmovies.api.APIPath
import com.example.arildojunior.upcomingmovies.api.ImageSizes
import com.example.arildojunior.upcomingmovies.model.Movie

class MoviesAdapter(var movies: List<Movie>, var clickListener: View.OnClickListener) :
        RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.releaseDate.text = movie.releaseDate
        holder.genre.text = "Genre"

        Glide.with(holder.itemView.context)
                .load(APIPath.getImagePath(ImageSizes.W154, movie.posterPath))
                .into(holder.movie_item)
    }

    fun getMovie(adapterPosition: Int): Movie {
        return movies[adapterPosition]
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var releaseDate: TextView
        var genre: TextView
        var movie_item: ImageView

        init {
            itemView.setOnClickListener(clickListener)
            itemView.tag = this
            title = itemView.findViewById(R.id.title) as TextView
            releaseDate = itemView.findViewById(R.id.release_date) as TextView
            genre = itemView.findViewById(R.id.genre) as TextView
            movie_item = itemView.findViewById(R.id.movie_item) as ImageView
        }
    }


}
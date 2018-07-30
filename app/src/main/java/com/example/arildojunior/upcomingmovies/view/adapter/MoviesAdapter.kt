package com.example.arildojunior.upcomingmovies.view.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.arildojunior.upcomingmovies.R
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB

class MoviesAdapter : PagedListAdapter<MovieDB, MoviesViewHolder>(movieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.render(movie)
        } else {
            holder.clear()
        }
    }

    companion object {
        private val movieDiffCallback = object : DiffUtil.ItemCallback<MovieDB>() {
            override fun areItemsTheSame(oldItem: MovieDB, newItem: MovieDB): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieDB, newItem: MovieDB): Boolean {
                return oldItem == newItem
            }
        }
    }
}
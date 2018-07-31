package com.example.arildojunior.upcomingmovies.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.example.arildojunior.upcomingmovies.R
import com.example.arildojunior.upcomingmovies.data.bus.RxBus
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB
import com.example.arildojunior.upcomingmovies.extension.removeBrackets
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity (): AppCompatActivity() {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        disposables.add(RxBus.getSubject().subscribe({
            if (it is MovieDB) {
                val movie = it
                if (movie.backdropPath.length > 0) {
                    Glide.with(bg_details.context).load(movie.backdropPath).into(bg_details)
                } else {
                    bg_details.visibility = View.GONE
                }
                if (movie.posterPath.length > 0) {
                    Glide.with(poster_details.context).load(movie.posterPath).into(poster_details)
                }
                title_details.text = movie.title
                release_date_details.text = movie.releaseDate
                genre_details.text = movie.genreNames.toString().removeBrackets()
                overview_details.text = movie.overview
            }
        }))
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            return intent
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}
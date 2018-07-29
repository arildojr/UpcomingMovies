package com.example.arildojunior.upcomingmovies.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import com.example.arildojunior.upcomingmovies.data.repository.MoviesRepository
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB
import com.example.arildojunior.upcomingmovies.data.api.model.Movie
import java.util.concurrent.TimeUnit


class MoviesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {

    private val disposables = CompositeDisposable()

    var pagedListMovie = MutableLiveData<PagedList<MovieDB>>()
    private val _currentSearch: MutableLiveData<String> = MutableLiveData()
    private val _moviesList: MutableLiveData<List<Movie>> = MutableLiveData()
    private val _progressVisibility: MutableLiveData<Boolean> = MutableLiveData()

    val currentSearch :LiveData<String>
        get() = _currentSearch
    val moviesList :LiveData<List<Movie>>
        get() = _moviesList
    val progressVisibility :LiveData<Boolean>
        get() = _progressVisibility

    fun getUpcomingMovies () {
        _progressVisibility.value = true
        disposables.add(moviesRepository.fetchUpcomingMovies()
                .subscribe({
                    pagedListMovie.value = it
                    _progressVisibility.value = false
                }, { it.printStackTrace() }))
    }

    fun searchObservableReady(searchObservable: Observable<String>) {
        disposables.add(
                searchObservable.debounce(1000, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { text ->
                                    _currentSearch.value = text
                                    onNewSearchQuery(text)
                                },
                                { t -> throw OnErrorNotImplementedException(t) }))
    }

    private fun onNewSearchQuery(text: String?) {
        if (text == null || text.length < 1) {
            return
        }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}

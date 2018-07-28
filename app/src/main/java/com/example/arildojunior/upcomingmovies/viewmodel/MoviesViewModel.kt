package com.example.arildojunior.upcomingmovies.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.schedulers.Schedulers
import com.example.arildojunior.upcomingmovies.api.APIClient
import com.example.arildojunior.upcomingmovies.model.Movie
import com.example.arildojunior.upcomingmovies.model.MoviesList
import java.util.concurrent.TimeUnit


class MoviesViewModel(private val apiClient: APIClient): ViewModel() {

    private val disposables = CompositeDisposable()

    private val _currentSearch: MutableLiveData<String> = MutableLiveData()
    private val _moviesList: MutableLiveData<List<Movie>> = MutableLiveData()
    private val _progressVisibility: MutableLiveData<Boolean> = MutableLiveData()

    val currentSearch :LiveData<String>
        get() = _currentSearch
    val moviesList :LiveData<List<Movie>>
        get() = _moviesList
    val progressVisibility :LiveData<Boolean>
        get() = _progressVisibility

    fun fetchUpcomingMovies (page: Int) {
        _progressVisibility.value = true
        disposables.add(
                apiClient.getUpcomingMoviesObservable(page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ list: MoviesList? ->
                            onMoviesReceived(list)
                        }))
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

    private fun onMoviesReceived(list: MoviesList?) {
        _progressVisibility.value = false
        if (list?.upcomingMovies?.size != null) {
            _moviesList.value = list.upcomingMovies
        }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}

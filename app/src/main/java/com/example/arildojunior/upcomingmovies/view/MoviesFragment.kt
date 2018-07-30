package com.example.arildojunior.upcomingmovies.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.InputMethodManager
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import com.example.arildojunior.upcomingmovies.App
import com.example.arildojunior.upcomingmovies.R
import com.example.arildojunior.upcomingmovies.data.room.model.MovieDB
import com.example.arildojunior.upcomingmovies.view.adapter.MoviesAdapter
import com.example.arildojunior.upcomingmovies.viewmodel.MoviesViewModel
import com.example.arildojunior.upcomingmovies.viewmodel.ViewModelFactory
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(){

    companion object {
        fun newInstance() = MoviesFragment()
    }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MoviesViewModel

    private var moviesAdapter: MoviesAdapter = MoviesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        App.component.inject(this)
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        initViewModel()
        initAdapter()
        setSearchListener()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)
        viewModel.currentSearch.observe(this, Observer { query -> textView.text = query })
        viewModel.progressVisibility.observe(this, Observer { visibility ->
            progressBar.visibility = if (visibility!!) View.VISIBLE else View.GONE
        })
        viewModel.getUpcomingMovies()
    }

    private fun initAdapter() {
        recyclerView.adapter = moviesAdapter
        viewModel.pagedListMovie.observe(this, Observer<PagedList<MovieDB>> {
            moviesAdapter.submitList(it)
        })
    }

    private fun setSearchListener() {
        val searchObservable = Observable.create(ObservableOnSubscribe<String> { subscriber ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) = Unit
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){
                    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0)
                    subscriber.onNext(s.toString())
                }
            })
        })

        viewModel.searchObservableReady(searchObservable)
    }


//    //View.OnClickListener
//    override fun onClick(v: View?) {
//        val holder = v?.tag as MoviesAdapter.MovieViewHolder
//        val movie = moviesAdapter?.getMovie(holder.adapterPosition)
//
//        movie?.let {
//            //mPresenter.onImageClick(it)
//        }
//    }

}

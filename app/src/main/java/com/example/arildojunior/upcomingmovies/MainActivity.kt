package com.example.arildojunior.upcomingmovies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.arildojunior.upcomingmovies.view.MoviesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMainFragment(savedInstanceState)
    }

    private fun setMainFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MoviesFragment.newInstance())
                    .commitNow()
        }
    }

}

package com.alphacorporations.moviesgallery.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphacorporations.moviesgallery.R
import com.alphacorporations.moviesgallery.activity.adapter.MoviesAdapter
import com.alphacorporations.moviesgallery.model.Movie
import com.alphacorporations.moviesgallery.repository.MoviesRepository
import kotlinx.android.synthetic.main.activity_list_movie.*


class MainActivity : AppCompatActivity() {

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var popularMoviesLayoutMgr: GridLayoutManager

    private var popularMoviesPage = 1
    private val topOfList = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)

        btn_return_top.setOnClickListener { list_popular_movie.smoothScrollToPosition(topOfList) }

        setAdapter()
        getPopularMovies()
    }

    private fun setAdapter() {
        popularMoviesLayoutMgr = GridLayoutManager(this, 2)
        list_popular_movie.layoutManager = popularMoviesLayoutMgr

        moviesAdapter = MoviesAdapter(mutableListOf())
        list_popular_movie.adapter = moviesAdapter

        loading_movie.visibility = View.VISIBLE
    }

    private fun getPopularMovies() {
        MoviesRepository.getPopularMovies(
            popularMoviesPage,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        moviesAdapter.appendMovies(movies)
        loading_movie.visibility = View.GONE
        attachPopularMoviesOnScrollListener()
    }

    /**infinity Scrolling**/
    private fun attachPopularMoviesOnScrollListener() {
        list_popular_movie.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularMoviesLayoutMgr.itemCount
                val visibleItemCount = popularMoviesLayoutMgr.childCount
                val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    list_popular_movie.removeOnScrollListener(this)
                    popularMoviesPage++
                    getPopularMovies()
                }
            }
        })
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }
}
package com.alphacorporations.moviesgallery.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alphacorporations.moviesgallery.CONSTANT
import com.alphacorporations.moviesgallery.R
import com.alphacorporations.moviesgallery.activity.adapter.MoviesAdapter
import com.alphacorporations.moviesgallery.model.Movie
import com.alphacorporations.moviesgallery.repository.MoviesRepository
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import kotlinx.android.synthetic.main.activity_list_movie.*
import kotlinx.android.synthetic.main.activity_profile_movie.*

/**
Created by Alph4 le 10/09/2020.
Projet: Movies Gallery
 **/
class MovieProfileActivity : AppCompatActivity() {

    private lateinit var moviesAdapter: MoviesAdapter
    private var movieID = CONSTANT.MOVIE_ID
    private var popularMoviesPage = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_movie)

        getPopularMovies()
        getExtraFromIntent()
        getSimilarMovie(movieID!!)
        //getVideoFromApi(movieID!!)
    }

    /** Get movie from MovieAPI with ID of Movie**/
    private fun getPopularMovies() {
        /** First try request with MovieID, request doesn't work**/
        /*  MoviesRepository.getPopularMoviesByID(
              movieID,
              ::MovieData,
              ::onError
          )*/
    }

    /** Get movieData from Intent **/
    private fun getExtraFromIntent() {
        println()
        /** Second try get data from intent to draw it, error = intent.getStringExtra("movie_id") must not be null**/
        drawMovie(movie = Movie(
            intent.extras?.get("movie_id") as Int,
            intent.extras?.get("movie_title") as String,
            intent.extras?.get("movie_overview") as String,
            intent.extras?.get("movie_poster_path") as String,
            intent.extras?.get("movie_backdrop_path") as String,
            intent.extras?.get("movie_vote_average") as Float,
            intent.extras?.get("movie_release_date") as String
        )
        )
    }

    private fun getSimilarMovie(movieID: Int) {
        MoviesRepository.getSimilarMovies(
            movieID,
            popularMoviesPage,
            ::onPopularMoviesFetched,
            ::onError

        )
    }
    private fun onPopularMoviesFetched(movies: List<Movie>) {
        moviesAdapter.appendMovies(movies)
        loading_movie.visibility = View.GONE
    }


    private fun drawMovie(movie: Movie) {

        Glide.with(movie_poster_path)
            .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
            .transform(CenterInside())
            .into(movie_poster_path)

        movie_title.text = movie.title
        movie_overview.text = movie.overview
        movie_vote_average.text = movie.rating.toString()
        movie_movie_release_date.text = movie.releaseDate.replace('-', '/')
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }
}
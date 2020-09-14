package com.alphacorporations.moviesgallery.event

import android.content.Intent
import android.view.View
import com.alphacorporations.moviesgallery.CONSTANT
import com.alphacorporations.moviesgallery.activity.MovieProfileActivity
import com.alphacorporations.moviesgallery.model.Movie

/**
Created by Alph4 le 10/09/2020.
Projet: Movies Gallery
 **/
class OpenMovieEvent {
    fun openMovieEvent(v: View, movie: Movie) {
        val intent = Intent(v.context, MovieProfileActivity::class.java)

        /** First try get movieID to make new request with this to MovieAPI**/
        CONSTANT.MOVIE_ID = movie.id

        /** Second try with data in a bundle of intent**/
        intent.putExtra("movie_id", movie.id)
        intent.putExtra("movie_title", movie.title)
        intent.putExtra("movie_overview", movie.overview)
        intent.putExtra("movie_poster_path", movie.posterPath)
        intent.putExtra("movie_backdrop_path", movie.backdropPath)
        intent.putExtra("movie_vote_average", movie.rating)
        intent.putExtra("movie_release_date", movie.releaseDate)

        v.context.startActivity(intent)
    }
}
package com.alphacorporations.moviesgallery.activity.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alphacorporations.moviesgallery.R
import com.alphacorporations.moviesgallery.event.OpenMovieEvent
import com.alphacorporations.moviesgallery.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import kotlinx.android.synthetic.main.item_movie.view.*
import org.greenrobot.eventbus.EventBus


/**
Created by Alph4 le 10/09/2020.
Projet: Movies Gallery
 **/
class MoviesAdapter(private var movies: MutableList<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun appendMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyItemRangeInserted(
            this.movies.size, movies.size - 1
        )
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterInside())
                .into(itemView.movie_item_img)

            itemView.movie_item_title.text = movie.title
            itemView.movie_item_date.text = movie.releaseDate.replace('-', '/')
            itemView.setOnClickListener {
                openMoviePage(movie, it)
            }

        }

        private fun openMoviePage(movie: Movie, view: View) {
            EventBus.getDefault().post(OpenMovieEvent().openMovieEvent(view, movie))
        }
    }

}

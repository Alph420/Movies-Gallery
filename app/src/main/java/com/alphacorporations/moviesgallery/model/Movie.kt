package com.alphacorporations.moviesgallery.model

import com.google.gson.annotations.SerializedName

/**
Created by Alph4 le 10/09/2020.
Projet: Movies Gallery
 **/
data class Movie (
    @SerializedName("id") val id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("vote_average") var rating: Float,
    @SerializedName("release_date") var releaseDate: String
)
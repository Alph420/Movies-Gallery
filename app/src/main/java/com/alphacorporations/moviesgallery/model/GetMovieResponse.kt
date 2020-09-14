package com.alphacorporations.moviesgallery.model

import com.google.gson.annotations.SerializedName

/**
Created by Alph4 le 10/09/2020.
Projet: Movies Gallery
 **/
data class GetMovieResponse (
    @SerializedName("video") val video: Boolean,
)
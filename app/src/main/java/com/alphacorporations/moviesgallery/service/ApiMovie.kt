package com.alphacorporations.moviesgallery.service

import com.alphacorporations.moviesgallery.model.GetMovieResponse
import com.alphacorporations.moviesgallery.model.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
Created by Alph4 le 10/09/2020.
Projet: Movies Gallery
 **/
interface ApiMovie {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "47c48be7b9913d1deac8b07d15648980",
        @Query("language") language:String = "fr",
        @Query("region") region:String = "FR",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
}
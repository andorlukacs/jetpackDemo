package com.example.jetpackdemo.data.remote

import com.example.jetpackdemo.domain.model.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        private const val API_KEY = "411f9106"
    }

    @GET("/")
    suspend fun searchMovies(
        @Query("s") title: String?,
        @Query("apikey") apiKey: String = API_KEY): MovieSearchResponse
}
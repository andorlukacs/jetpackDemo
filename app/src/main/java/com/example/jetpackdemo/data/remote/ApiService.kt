package com.example.jetpackdemo.data.remote

import com.example.jetpackdemo.data.remote.dto.MovieSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun searchMovies(
        @Query("s") title: String?,
        @Query("apikey") apiKey: String
    ): MovieSearchResponseDto
}
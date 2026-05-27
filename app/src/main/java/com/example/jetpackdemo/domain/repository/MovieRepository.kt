package com.example.jetpackdemo.domain.repository

import com.example.jetpackdemo.domain.model.Movie

interface MovieRepository {
    suspend fun searchMovies(query: String): List<Movie>
}
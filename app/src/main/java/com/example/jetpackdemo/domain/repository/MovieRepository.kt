package com.example.jetpackdemo.domain.repository

import com.example.jetpackdemo.domain.model.Movie

interface MovieRepository {
    fun searchMovies(query: String): List<Movie>
}
package com.example.jetpackdemo.data.repository

import com.example.jetpackdemo.data.remote.ApiService
import com.example.jetpackdemo.domain.model.Movie
import com.example.jetpackdemo.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl(
    var apiService: ApiService,
    ) : MovieRepository {
    override fun searchMovies(query: String): List<Movie> {
        TODO("Not yet implemented")
    }
}
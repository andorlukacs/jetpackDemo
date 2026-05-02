package com.example.jetpackdemo.domain.usecase

import com.example.jetpackdemo.domain.model.Movie
import com.example.jetpackdemo.domain.repository.MovieRepository

class SearchMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(query: String): List<Movie> = repository.searchMovies(query)
}
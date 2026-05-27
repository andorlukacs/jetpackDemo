package com.example.jetpackdemo.data.repository

import com.example.jetpackdemo.data.remote.ApiService
import com.example.jetpackdemo.data.remote.dto.MovieDto
import com.example.jetpackdemo.domain.model.Movie
import com.example.jetpackdemo.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {

    override suspend fun searchMovies(query: String): List<Movie> {
        val response = apiService.searchMovies(title = query, apiKey = API_KEY)
        if (response.error != null) throw Exception(response.error)
        return response.movies?.map { it.toDomain() } ?: emptyList()
    }

    private fun MovieDto.toDomain() = Movie(
        title = title,
        year = year,
        imdbID = imdbID,
        type = type,
        poster = poster
    )

    companion object {
        private const val API_KEY = "411f9106"
    }
}
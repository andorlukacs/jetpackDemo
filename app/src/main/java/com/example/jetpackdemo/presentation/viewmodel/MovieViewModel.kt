package com.example.jetpackdemo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackdemo.domain.model.Movie
import com.example.jetpackdemo.domain.usecase.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MovieState {
    object Loading : MovieState()
    data class Error(val message: String) : MovieState()
    data class Content(val movies: List<Movie>) : MovieState()
}

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MovieState>(MovieState.Loading)
    val state: StateFlow<MovieState> = _state.asStateFlow()

    fun getMovies(query: String) {
        _state.value = MovieState.Loading
        viewModelScope.launch {
            try {
                val movies = searchMoviesUseCase(query)
                _state.value = MovieState.Content(movies)
            } catch (e: Exception) {
                _state.value = MovieState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
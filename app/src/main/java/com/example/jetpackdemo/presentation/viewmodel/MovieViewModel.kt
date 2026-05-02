package com.example.jetpackdemo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackdemo.data.remote.ApiService
import com.example.jetpackdemo.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MovieState{
    object Loading : MovieState()
    data class Error(val message: String) : MovieState()
    data class Content(val movies: List<Movie>) : MovieState()
}

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {


    var state = MutableStateFlow<MovieState>(MovieState.Loading)

    fun getMovies() {
        state.value = MovieState.Loading

        viewModelScope.launch {
            val response = api.searchMovies("Matrix")

            if (response.error != null) {
                state.value = MovieState.Error(response.error)
                return@launch
            }

            state.value = MovieState.Content(response.movies ?: emptyList())
        }
    }
}
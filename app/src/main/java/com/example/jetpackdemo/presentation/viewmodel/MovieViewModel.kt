package com.example.jetpackdemo.presentation.viewmodel

import android.util.Log
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

//    companion object {
//        private const val TAG = "MovieViewModel"
//    }

    var state = MutableStateFlow<MovieState>(MovieState.Loading)

    fun getMovies(query: String) {
        state.value = MovieState.Loading
        Log.d("Nobb", "Loading movies...")

        viewModelScope.launch {
            Log.d("NOBB", "Initiating backend call")
            val currentTime = System.currentTimeMillis()
            val response = api.searchMovies(query)
            var endTime = System.currentTimeMillis()
            Log.d("NOBB", "Backend call done in: ${(endTime - currentTime)} milliseconds")

            if (response.error != null) {
                Log.e("NOBB", "Error: ${response.error}")
                state.value = MovieState.Error(response.error)
                return@launch
            }
            Log.d("NOBB", "Success: ${response.movies?.size} results")
            state.value = MovieState.Content(response.movies ?: emptyList())
        }
    }
}
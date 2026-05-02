package com.example.jetpackdemo.presentation.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackdemo.presentation.ui.theme.JetpackDemoTheme
import com.example.jetpackdemo.presentation.viewmodel.MovieState
import com.example.jetpackdemo.presentation.viewmodel.UiState

@Composable
fun MovieScreen(
    movieState: MovieState,
    onGetMovies: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            when (movieState) {
                is MovieState.Loading -> {
                    MovieItem("Loading...")
                }

                is MovieState.Error -> {
                    MovieItem("Error.")
                }

                is MovieState.Content -> {
                    movieState.movies.forEach { movie ->
                        MovieItem(movie.title)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(name: String?, modifier: Modifier = Modifier) {
    Text(
        text = "Movie: $name",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun DemoScreenPreview() {
    JetpackDemoTheme {
        MovieScreen(
            movieState = MovieState.Loading,
            onGetMovies = {}
        )
    }
}
package com.example.jetpackdemo.presentation.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackdemo.presentation.ui.theme.JetpackDemoTheme
import com.example.jetpackdemo.presentation.viewmodel.MovieState

@Composable
fun MovieScreen(
    movieState: MovieState,
    onGetMovies: (query: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SearchHeader(onGetMovies)
            when (movieState) {
                is MovieState.Loading -> {
                }

                is MovieState.Error -> {
                    MovieItem("Error.")
                }

                is MovieState.Content -> {
                    LazyColumn {
                        items(movieState.movies) { movie ->
                            MovieItem(movie.title)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchHeader(
    onGetMovies: (query: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(align = Alignment.CenterVertically),
            text = "Search for movies: ",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.width(8.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            value = text,
            onValueChange = { newText -> text = newText },
            singleLine = true,
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onGetMovies(text.text) }
        ) {
            Text("Search")
        }
    }
}

@Composable
fun MovieItem(
    name: String?,
    modifier: Modifier = Modifier
) {
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
            movieState = MovieState.Error("Error"),
            onGetMovies = {}
        )
    }
}
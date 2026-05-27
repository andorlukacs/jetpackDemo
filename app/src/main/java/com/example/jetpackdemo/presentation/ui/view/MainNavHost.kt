package com.example.jetpackdemo.presentation.ui.view

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackdemo.common.navigation.Screen
import com.example.jetpackdemo.presentation.viewmodel.DemoViewModel
import com.example.jetpackdemo.presentation.viewmodel.MovieViewModel

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    counterViewModel: DemoViewModel,
    movieViewModel: MovieViewModel,
) {
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val counterState by counterViewModel.uiState.collectAsStateWithLifecycle()
    val movieState by movieViewModel.state.collectAsStateWithLifecycle()
    NavHost(
        modifier = modifier.consumeWindowInsets(WindowInsets.systemBars),
        navController = navController,
        startDestination = Screen.Movies.route
    ) {
        composable(route = Screen.Counter.route) {
            CounterScreen(
                uiState = counterState,
                onIncrement = counterViewModel::increment,
                onDecrement = counterViewModel::decrement
            )
        }

        composable(route = Screen.Movies.route) {
            MovieScreen(
                movieState = movieState,
                onGetMovies = movieViewModel::getMovies
            )
        }
    }
}
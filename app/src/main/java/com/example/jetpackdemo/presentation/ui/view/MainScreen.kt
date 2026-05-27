package com.example.jetpackdemo.presentation.ui.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackdemo.presentation.ui.theme.JetpackDemoTheme
import com.example.jetpackdemo.presentation.viewmodel.DemoViewModel
import com.example.jetpackdemo.presentation.viewmodel.MovieViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    counterViewModel: DemoViewModel = hiltViewModel(),
    movieViewModel: MovieViewModel = hiltViewModel(),
) {
    JetpackDemoTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar = {
                MainBottomBar(
                    navController = navController,
                    currentDestination = currentDestination,
                )
            }
        ) { innerPadding ->
            Row {
                MainNavHost(
                    navController = navController,
                    counterViewModel = counterViewModel,
                    movieViewModel = movieViewModel,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DemoScreenPreview() {
    JetpackDemoTheme {
        MainScreen()
    }
}
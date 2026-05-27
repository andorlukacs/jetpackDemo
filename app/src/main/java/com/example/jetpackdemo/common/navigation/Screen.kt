package com.example.jetpackdemo.common.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackdemo.R

sealed class Screen(
    val route: String,
    @StringRes val labelResourceId: Int,
    val icon: ImageVector
) {
    data object Counter: Screen("counter", R.string.counter_screen_title, Icons.Filled.Add)
    data object Movies: Screen("movies", R.string.movies_screen_title, Icons.Filled.PlayArrow)
}

val topLevelScreens = listOf( Screen.Movies, Screen.Counter)
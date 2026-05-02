package com.example.jetpackdemo.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackdemo.presentation.ui.theme.JetpackDemoTheme
import com.example.jetpackdemo.presentation.ui.view.DemoScreen
import com.example.jetpackdemo.presentation.ui.view.MovieItem
import com.example.jetpackdemo.presentation.ui.view.MovieScreen
import com.example.jetpackdemo.presentation.viewmodel.MovieState
import com.example.jetpackdemo.presentation.viewmodel.MovieViewModel
import kotlin.getValue

class MoviesFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )
            : View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            JetpackDemoTheme {
                val uiState by viewModel.state.collectAsStateWithLifecycle()

                MovieScreen(
                    movieState = uiState,
                    onGetMovies = viewModel::getMovies
                )
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
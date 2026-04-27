package com.example.jetpackdemo.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackdemo.presentation.viewmodel.DemoViewModel
import com.example.jetpackdemo.ui.theme.JetpackDemoTheme
import androidx.fragment.app.viewModels
import com.example.jetpackdemo.presentation.viewmodel.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoFragment : Fragment() {

    private val viewModel: DemoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {


        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            JetpackDemoTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                DemoScreen(
                    uiState = uiState,
                    onIncrement = viewModel::increment,
                    onDecrement = viewModel::decrement
                )
            }
        }
    }
}

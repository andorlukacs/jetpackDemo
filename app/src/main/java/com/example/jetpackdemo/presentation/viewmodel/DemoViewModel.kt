package com.example.jetpackdemo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState {
    object Loading : UiState()
    data class Success(val count: Int, val message: String) : UiState()
    data class Error(val message: String) : UiState()
}

@HiltViewModel
class DemoViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(0, "Starting"))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun increment() {
        when (val currentState = _uiState.value) {
            is UiState.Loading -> Unit

            is UiState.Success -> {
                viewModelScope.launch {
                    _uiState.update { UiState.Loading }
                    delay(1000)
                    _uiState.update { UiState.Success(currentState.count + 1, "Success") }
                }
            }

            is UiState.Error -> {
                viewModelScope.launch {
                    _uiState.update { UiState.Loading }
                    delay(1000)
                    _uiState.update { UiState.Success(1, "Success") }
                }
            }
        }
    }

    fun decrement() {
        _uiState.update { UiState.Error("Error") }
    }
}
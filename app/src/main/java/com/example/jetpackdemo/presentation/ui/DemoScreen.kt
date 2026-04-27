package com.example.jetpackdemo.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackdemo.presentation.viewmodel.UiState
import com.example.jetpackdemo.ui.theme.JetpackDemoTheme

@Composable
fun DemoScreen(
    uiState: UiState,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text = ""
        var message = ""
        when (uiState) {
            is UiState.Loading ->
                text = "Loading..."
            is UiState.Success -> {
                text = uiState.count.toString()
                message = uiState.message
            }
            is UiState.Error -> {
                message = uiState.message
            }
        }
        Text(
            text = message,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Count: ${text}",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = onDecrement) { Text("-") }
            Button(onClick = onIncrement) { Text("+") }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DemoScreenPreview() {
    JetpackDemoTheme {
        DemoScreen(
            uiState = UiState.Success(0, "Starting"),
            onIncrement = {},
            onDecrement = {}
        )
    }
}
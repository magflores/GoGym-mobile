package com.example.myapplication

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExerciseDetailedTopBar() {
    TopAppBar(
        title = { Text(text = "Exercise") },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        }
    )
}

@Preview
@Composable
fun ExerciseDetailed() {
    Scaffold(
        topBar = { ExerciseDetailedTopBar() },
        content = { Text(text = "Exercise") }
    )
}

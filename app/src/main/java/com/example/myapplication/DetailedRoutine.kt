package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailedRoutine(routineId: Int, onBack: () -> Unit) {
    Scaffold(
        topBar = {
                 RoutineTopBar(onBack)
        },
        floatingActionButton = {

        }
    ) { paddingValues ->
        Column() {

        }
    }
}

@Composable
fun RoutineTopBar(onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text("Routine")
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icons.Filled.ArrowBack
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icons.Filled.Favorite
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icons.Filled.Menu
            }
        }
    )
}

@Preview
@Composable
fun DefaultRoutinePreview() {
    DetailedRoutine(1, {})
}


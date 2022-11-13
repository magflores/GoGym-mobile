package com.example.myapplication.ui.screens.detail

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

@Composable
fun RoutineTopBar(onBack: () -> Unit) {
    TopAppBar(title = {
        Text("Routine")
    }, navigationIcon = {
        IconButton(onClick = onBack) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
    }, actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Menu, contentDescription = "Menu")
        }
    })
}

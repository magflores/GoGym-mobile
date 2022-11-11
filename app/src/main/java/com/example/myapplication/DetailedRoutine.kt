package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailedRoutine(navController: NavController, routineId: Int, onBack: () -> Unit) {
    BottomNavLayout(navController = navController) {
        Scaffold(
            topBar = {
                RoutineTopBar(onBack)
            },
            floatingActionButton = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icons.Filled.PlayArrow
                }
            }
        ) { paddingValues ->
            Column() {
                DetailedRoutineInfo(routineId)
            }
        }
    }

}


@Composable
fun DetailedRoutineInfo(routineId: Int) {
    // Surface at the top of the screen with the routine name and description
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .defaultMinSize(minHeight = 200.dp),
        color = Color(0xFFE0E0E0)
    ) {
        Text(text = "hello")
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
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
    )
}


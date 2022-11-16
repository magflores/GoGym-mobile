package com.example.myapplication.ui.screens.favroutines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.myapplication.BottomNavLayout
import com.example.myapplication.Screen

@Composable
fun FavRoutines(navController: NavController, onNotLoggedIn: () -> Unit, viewModel: FavRoutinesViewModel) {
    LaunchedEffect(false) {
        if (false)
            onNotLoggedIn()
    }
    val uiState = viewModel.uiState
    BottomNavLayout(navController = navController) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "FAV ROUTINES")
            Button(onClick = { navController.navigate("${Screen.RoutineScreen.route}/0") }) {
                Text(text = "GO TO ROUTINE")
            }
        }
//        TODO make fav routines screen
    }
}
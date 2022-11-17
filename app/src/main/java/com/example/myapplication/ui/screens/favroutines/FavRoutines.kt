package com.example.myapplication.ui.screens.favroutines

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.BottomNavLayout
import com.example.myapplication.Screen
import com.example.myapplication.ui.ExampleViewModel

@Composable
fun FavRoutines(navController: NavController, onNotLoggedIn: () -> Unit, viewModel: FavRoutinesViewModel, mainViewModel: ExampleViewModel) {
    LaunchedEffect(mainViewModel.uiState.isAuthenticated) {
        if (! mainViewModel.uiState.isAuthenticated)
            onNotLoggedIn()
    }
    val uiState = viewModel.uiState
    BottomNavLayout(navController = navController) { padding ->
//        Column(verticalArrangement = Arrangement.Center) {
//            Text(text = "FAV ROUTINES")
//            Button(onClick = { navController.navigate("${Screen.RoutineScreen.route}/0") }) {
//                Text(text = "GO TO ROUTINE")
//            }
//        }
//        TODO make fav routines screen
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            Button(onClick = {     viewModel.getFavourites() }) {
                Text(text = "GET")
            }
            Button(onClick = { navController.navigate("${Screen.RoutineScreen.route}/1") }) {
                Text(text = "GO TO ROUTINE")
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                modifier = Modifier
//                    .padding(padding)
                    .fillMaxWidth(),
            ) {
                uiState.favourites?.forEach() {
                    this.item{
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Text(text = it.name)
                        }
                    }
                }
            }
        }
        
    }
}
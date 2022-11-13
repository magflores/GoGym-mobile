package com.example.myapplication.ui.screens.play

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myapplication.BottomNavLayout
import com.example.myapplication.ui.screens.detail.RoutineTopBar

@Composable
fun PlayRoutine(navController: NavController, routineId: Int, onBack: () -> Unit) {
    BottomNavLayout(navController = navController) { bottomNavPadding ->
        Scaffold(
            topBar = { RoutineTopBar(onBack) },
            modifier = Modifier.padding(bottomNavPadding)
        ) { padding ->
            Text(text = "Hello", modifier = Modifier.padding(padding))
        }
    }
}
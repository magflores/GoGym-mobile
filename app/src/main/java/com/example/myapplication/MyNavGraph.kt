package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.FavRoutinesScreen.route
    ) {
        composable(Screen.FavRoutinesScreen.route) {
            FavRoutines(navController)
        }
        composable(Screen.MyRoutinesScreen.route) {
            MyRoutines(navController)
        }
        composable(Screen.AllRoutinesScreen.route) {
            AllRoutines(navController)
        }
        composable(Screen.LogInScreen.route) {
            LogIn()
        }
    }
}
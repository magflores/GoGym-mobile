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
        val onNotLoggedIn = { navController.navigate(Screen.LogInScreen.route) }
        composable(Screen.FavRoutinesScreen.route) {
            FavRoutines(navController, onNotLoggedIn)
        }
        composable(Screen.MyRoutinesScreen.route) {
            MyRoutines(navController, onNotLoggedIn)
        }
        composable(Screen.AllRoutinesScreen.route) {
            AllRoutines(navController, onNotLoggedIn)
        }
        composable(Screen.LogInScreen.route) {
            LogIn()
        }
        composable(Screen.RoutineScreen.route) { backStackEntry ->
            val routineId = backStackEntry.arguments?.getInt("routineId") ?: 0
            DetailedRoutine(navController, routineId, onBack = { navController.popBackStack() })
        }
    }
}
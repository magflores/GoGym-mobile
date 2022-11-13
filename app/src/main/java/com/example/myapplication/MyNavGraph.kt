package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.detail.DetailedRoutine
import com.example.myapplication.ui.screens.play.PlayRoutine

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
        composable(
            Screen.RoutineScreen.route + "/{routineId}",
            arguments = listOf(navArgument("routineId") { type = NavType.IntType })
        ) { backStackEntry ->
            val routineId = backStackEntry.arguments?.getInt("routineId") ?: 0
            DetailedRoutine(
                navController,
                routineId,
                onBack = { navController.popBackStack() },
                onPlay = {
                    navController.navigate("${Screen.PlayRoutineScreen.route}/$routineId")
                }
            )
        }
        composable(
            Screen.PlayRoutineScreen.route + "/{routineId}",
            arguments = listOf(navArgument("routineId") { type = NavType.IntType })
        ) { backStackEntry ->
            val routineId = backStackEntry.arguments?.getInt("routineId") ?: 0
            PlayRoutine(
                navController = navController,
                routineId = routineId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
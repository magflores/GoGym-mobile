package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.detail.DetailedRoutine
import com.example.myapplication.ui.screens.favroutines.FavRoutines
import com.example.myapplication.ui.screens.play.PlayRoutine
import com.example.myapplication.ui.screens.play.PlayViewModel
import com.example.myapplication.util.getViewModelFactory

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screen.FavRoutinesScreen.route
    ) {
        val onNotLoggedIn = { navController.navigate(Screen.LogInScreen.route) }
        composable(Screen.FavRoutinesScreen.route) {
            FavRoutines(
                navController = navController,
                onNotLoggedIn = onNotLoggedIn,
                viewModel = viewModel(factory = getViewModelFactory()),
                mainViewModel = viewModel(factory = getViewModelFactory())
            )
        }
        composable(Screen.MyRoutinesScreen.route) {
            MyRoutines(navController, onNotLoggedIn)
        }
        composable(Screen.AllRoutinesScreen.route) {
            AllRoutines(navController, onNotLoggedIn)
        }
        composable(Screen.LogInScreen.route) {
            LogIn(viewModel(factory = getViewModelFactory()), onLogIn = {
                navController.navigate(Screen.FavRoutinesScreen.route) {
                    popUpTo(Screen.FavRoutinesScreen.route)
                }
            })
        }
        composable(
            Screen.RoutineScreen.route + "/{routineId}",
            arguments = listOf(navArgument("routineId") { type = NavType.IntType })
        ) { backStackEntry ->
            val routineId = backStackEntry.arguments?.getInt("routineId") ?: 0
            DetailedRoutine(navController,
                routineId,
                onBack = { navController.popBackStack() },
                onPlay = {
                    navController.navigate("${Screen.PlayRoutineScreen.route}/$routineId")
                })
        }
        composable(
            Screen.PlayRoutineScreen.route + "/{routineId}",
            arguments = listOf(navArgument("routineId") { type = NavType.IntType })
        ) { backStackEntry ->
            val routineId = backStackEntry.arguments?.getInt("routineId") ?: 0
            val playViewModel: PlayViewModel = viewModel(factory = getViewModelFactory())
            PlayRoutine(
                navController = navController,
                routineId = routineId,
                onBack = { navController.popBackStack() },
                viewModel = playViewModel
            )
        }
    }
}
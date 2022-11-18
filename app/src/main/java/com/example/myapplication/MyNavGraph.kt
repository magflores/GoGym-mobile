package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.myapplication.ui.ExampleViewModel
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel
import com.example.myapplication.ui.screens.detail.DetailedRoutine
import com.example.myapplication.ui.screens.favroutines.FavRoutines
import com.example.myapplication.ui.screens.favroutines.FavRoutinesViewModel
import com.example.myapplication.ui.screens.play.PlayRoutine
import com.example.myapplication.ui.screens.play.PlayViewModel
import com.example.myapplication.util.getViewModelFactory

@Composable
fun MyNavGraph(navController: NavHostController, exampleViewModel: ExampleViewModel) {
    val favRoutinesViewModel: FavRoutinesViewModel = viewModel(factory = getViewModelFactory())
    val routinesViewModel: RoutinesViewModel = viewModel(factory = getViewModelFactory())
//    val playRoutinesViewModel: PlayViewModel = viewModel(factory = getViewModelFactory())
    val routineUri = BuildConfig.WEBPAGE_BASE_URL
    NavHost(
        navController = navController, startDestination = Screen.FavRoutinesScreen.route
    ) {
        val onNotLoggedIn = { navController.navigate(Screen.LogInScreen.route) }
        val goToRoutine: (Int) -> Unit =
            { navController.navigate(Screen.RoutineScreen.route + "/" + it.toString()) }
        composable(Screen.FavRoutinesScreen.route) {
            FavRoutines(
                navController = navController,
                onNotLoggedIn = onNotLoggedIn,
                viewModel = favRoutinesViewModel,
                mainViewModel = exampleViewModel,
                onGoToRoutine = goToRoutine,
                routinesViewModel = routinesViewModel
            )
        }
        composable(Screen.MyRoutinesScreen.route) {
            MyRoutines(
                navController,
                onNotLoggedIn,
                routinesViewModel = routinesViewModel,
                exampleViewModel = exampleViewModel,
                onGoToRoutine = goToRoutine,
            )
        }
        composable(Screen.AllRoutinesScreen.route) {
            AllRoutines(
                navController,
                onNotLoggedIn,
                routinesViewModel = routinesViewModel,
                exampleViewModel = exampleViewModel,
                onGoToRoutine = goToRoutine
            )
        }
        composable(Screen.LogInScreen.route) {
            LogIn(
                viewModel = exampleViewModel,
                onLogIn = {
                    navController.navigate(Screen.FavRoutinesScreen.route) {
                        popUpTo(Screen.FavRoutinesScreen.route)
                    }
                }
            )
        }
        composable(
            Screen.RoutineScreen.route + "/{routineId}",
            arguments = listOf(navArgument("routineId") { type = NavType.IntType }),
            deepLinks = listOf(navDeepLink { uriPattern = "$routineUri/{routineId}" })
        ) { backStackEntry ->
            val routineId = backStackEntry.arguments?.getInt("routineId") ?: 0
            DetailedRoutine(
                navController = navController,
                mainViewModel = exampleViewModel,
                routineId = routineId,
                onBack = { navController.popBackStack() },
                onPlay = {
                    navController.navigate("${Screen.PlayRoutineScreen.route}/$routineId")
                },
                routinesViewModel = routinesViewModel,
                favRoutinesViewModel = favRoutinesViewModel
            )
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
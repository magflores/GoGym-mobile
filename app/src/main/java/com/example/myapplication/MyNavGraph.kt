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
    val routineUriAddon = "/viewroutine"
    NavHost(
        navController = navController, startDestination = Screen.FavRoutinesScreen.route
    ) {
        val onNotLoggedIn = {
            navController.navigate(Screen.LogInScreen.route) {
                navController.backQueue.clear()
            }
        }
        val navigateOnLogout = {
            navController.navigate(Screen.LogInScreen.route) {
                navController.backQueue.clear()
            }
        }
        val goToRoutine: (Int) -> Unit =
            { navController.navigate(Screen.RoutineScreen.route + "/" + it.toString()) }
        composable(
            Screen.RoutineScreen.route + "/{routineId}",
            arguments = listOf(navArgument("routineId") { type = NavType.IntType }),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "${BuildConfig.WEBPAGE_BASE_URL}$routineUriAddon/{routineId}"
                },
                navDeepLink {
                    uriPattern = "${BuildConfig.WEBPAGE_BASE_URL2}$routineUriAddon/{routineId}"
                },
                navDeepLink {
                    uriPattern = "${BuildConfig.WEBPAGE_BASE_URL3}$routineUriAddon/{routineId}"
                },
                navDeepLink {
                    uriPattern = "${BuildConfig.WEBPAGE_BASE_URL4}$routineUriAddon/{routineId}"
                },
                navDeepLink {
                    uriPattern = "${BuildConfig.WEBPAGE_BASE_URL5}$routineUriAddon/{routineId}"
                },
                navDeepLink {
                    uriPattern = "${BuildConfig.WEBPAGE_BASE_URL6}$routineUriAddon/{routineId}"
                },
                )
        ) { backStackEntry ->
            val routineId = backStackEntry.arguments?.getInt("routineId") ?: 0
            DetailedRoutine(
                navController = navController,
                mainViewModel = exampleViewModel,
                routinesViewModel = routinesViewModel,
                routineId = routineId,
                onBack = { navController.popBackStack() }
            ) {
                navController.navigate("${Screen.PlayRoutineScreen.route}/$routineId")
            }
        }
        composable(
            Screen.FavRoutinesScreen.route,
            deepLinks = listOf(
                navDeepLink { uriPattern = BuildConfig.WEBPAGE_BASE_URL },
                navDeepLink { uriPattern = BuildConfig.WEBPAGE_BASE_URL2 },
                navDeepLink { uriPattern = BuildConfig.WEBPAGE_BASE_URL3 },
                navDeepLink { uriPattern = BuildConfig.WEBPAGE_BASE_URL4 },
                navDeepLink { uriPattern = BuildConfig.WEBPAGE_BASE_URL5 },
                navDeepLink { uriPattern = BuildConfig.WEBPAGE_BASE_URL6 }
            )
        ) {
            FavRoutines(
                navController = navController,
                onNotLoggedIn = onNotLoggedIn,
                viewModel = favRoutinesViewModel,
                mainViewModel = exampleViewModel,
                onGoToRoutine = goToRoutine,
                routinesViewModel = routinesViewModel,
                navigateOnLogout = navigateOnLogout
            )
        }
        composable(Screen.MyRoutinesScreen.route) {
            MyRoutines(
                navController,
                onNotLoggedIn,
                routinesViewModel = routinesViewModel,
                exampleViewModel = exampleViewModel,
                onGoToRoutine = goToRoutine,
                navigateOnLogout = navigateOnLogout
            )
        }
        composable(Screen.AllRoutinesScreen.route) {
            AllRoutines(
                navController,
                onNotLoggedIn,
                routinesViewModel = routinesViewModel,
                exampleViewModel = exampleViewModel,
                onGoToRoutine = goToRoutine,
                navigateOnLogout = navigateOnLogout
            )
        }
        composable(Screen.LogInScreen.route) {
            LogIn(
                viewModel = exampleViewModel,
                onLogIn = {
                    navController.navigate(Screen.FavRoutinesScreen.route) {
                        navController.backQueue.clear()
                    }
                }
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
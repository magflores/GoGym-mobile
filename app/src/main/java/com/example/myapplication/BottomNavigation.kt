package com.example.myapplication

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.ui.ExampleViewModel
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel
import com.example.myapplication.ui.screens.allroutines.RutinesScreen
import com.example.myapplication.ui.screens.allroutines.UserRoutinesScreen

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Screen.AllRoutinesScreen,
        Screen.MyRoutinesScreen,
        Screen.FavRoutinesScreen
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(item.titleId)
                    )
                },
                label = { Text(text = stringResource(item.titleId)) },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun BottomNavLayout(navController: NavController, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        content = content,
    )
}

@Composable
fun MyRoutines(
    navController: NavController,
    onNotLoggedIn: () -> Unit,
    routinesViewModel: RoutinesViewModel,
    exampleViewModel: ExampleViewModel
) {
    LaunchedEffect(exampleViewModel.uiState.isAuthenticated) {
        if (!exampleViewModel.uiState.isAuthenticated)
            onNotLoggedIn()
    }
    BottomNavLayout(navController = navController) {
        UserRoutinesScreen(
            padding = it,
            routinesViewModel = routinesViewModel,
            exampleViewModel = exampleViewModel
        )
    }
}

@Composable
fun AllRoutines(
    navController: NavController,
    onNotLoggedIn: () -> Unit,
    routinesViewModel: RoutinesViewModel,
    exampleViewModel: ExampleViewModel
) {
    LaunchedEffect(exampleViewModel.uiState.isAuthenticated) {
        if (!exampleViewModel.uiState.isAuthenticated)
            onNotLoggedIn()
    }
    BottomNavLayout(navController = navController) {
        RutinesScreen(padding = it, routinesViewModel = routinesViewModel)
//        TODO make all routines screen
    }
}

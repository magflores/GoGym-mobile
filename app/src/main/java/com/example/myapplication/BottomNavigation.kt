package com.example.myapplication

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.ui.ExampleViewModel
import com.example.myapplication.ui.hasError
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
fun BottomNavLayout(
    navController: NavController,
    mainViewModel: ExampleViewModel,
    content: @Composable (PaddingValues) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navController = navController) },
        content = content,
        scaffoldState = scaffoldState
    )
    val uiState = mainViewModel.uiState

    if (uiState.hasError) {
        val actionLabel = stringResource(R.string.dismiss)

        LaunchedEffect(scaffoldState.snackbarHostState) {
            val result = scaffoldState.snackbarHostState.showSnackbar(
                message = uiState.message!!,
                actionLabel = actionLabel
            )
            when (result) {
                SnackbarResult.Dismissed -> mainViewModel.dismissMessage()
                SnackbarResult.ActionPerformed -> mainViewModel.dismissMessage()
            }
        }
    }
}

@Composable
fun MyRoutines(
    navController: NavController,
    onNotLoggedIn: () -> Unit,
    routinesViewModel: RoutinesViewModel,
    exampleViewModel: ExampleViewModel,
    onGoToRoutine: (Int) -> Unit
) {
    LaunchedEffect(exampleViewModel.uiState.isAuthenticated) {
        if (!exampleViewModel.uiState.isAuthenticated)
            onNotLoggedIn()
    }
    BottomNavLayout(navController = navController, mainViewModel = exampleViewModel) {
        UserRoutinesScreen(
            padding = it,
            routinesViewModel = routinesViewModel,
            exampleViewModel = exampleViewModel,
            onGoToRoutine = onGoToRoutine
        )
    }
}

@Composable
fun AllRoutines(
    navController: NavController,
    onNotLoggedIn: () -> Unit,
    routinesViewModel: RoutinesViewModel,
    exampleViewModel: ExampleViewModel,
    onGoToRoutine: (Int) -> Unit
) {
    LaunchedEffect(exampleViewModel.uiState.isAuthenticated) {
        if (!exampleViewModel.uiState.isAuthenticated)
            onNotLoggedIn()
    }
    BottomNavLayout(navController = navController, mainViewModel = exampleViewModel) {
        RutinesScreen(
            padding = it,
            routinesViewModel = routinesViewModel,
            mainViewModel = exampleViewModel,
            onGoToRoutine = onGoToRoutine
        )
//        TODO make all routines screen
    }
}

package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.ui.ExampleViewModel

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
                icon = { Icon(imageVector = item.icon, contentDescription = stringResource(item.titleId)) },
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
fun MyRoutines(navController: NavController, onNotLoggedIn: () -> Unit) {
    LaunchedEffect(true) {
        if (true)
            onNotLoggedIn()
    }
    BottomNavLayout(navController = navController) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "MY ROUTINES")
        }
//        TODO make my routines screen
    }
}

@Composable
fun AllRoutines(navController: NavController, onNotLoggedIn: () -> Unit, viewModel: ExampleViewModel) {
    LaunchedEffect(false) {
        if (false)
            onNotLoggedIn()
    }
    BottomNavLayout(navController = navController) {
        RutinesScreen(it, viewModel)
//        TODO make all routines screen
    }
}

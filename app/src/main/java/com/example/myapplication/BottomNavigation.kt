package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

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
        content = content
    )
}

@Composable
fun FavRoutines(navController: NavController) {
    BottomNavLayout(navController = navController) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "FAV ROUTINES")
        }
//        TODO make fav routines screen
    }
}

@Composable
fun MyRoutines(navController: NavController) {
    /*pasarle un callback para lo de logged in, que redirige a la pantalla que corresponde
    * asi no hardcodeamos la direccion en cada pantalla*/
    BottomNavLayout(navController = navController) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "MY ROUTINES")
        }
//        TODO make my routines screen
    }
}

@Composable
fun AllRoutines(navController: NavController) {
    BottomNavLayout(navController = navController) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "ALL ROUTINES")
        }
//        TODO make all routines screen
    }
}

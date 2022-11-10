package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector, val route:String) {
    object FavRoutinesScreen: Screen("Favorite Routines", Icons.Filled.Favorite, "fav_routines_screen")
    object AllRoutinesScreen: Screen("All Routines", Icons.Filled.Edit, "all_routines_screen")
    object MyRoutinesScreen: Screen("My Routines", Icons.Filled.Edit, "my_routines_screen")
    object LogInScreen: Screen("Log In", Icons.Default.ArrowDropDown, "login_screen")
}
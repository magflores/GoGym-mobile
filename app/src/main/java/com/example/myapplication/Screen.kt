package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val titleId: Int, val icon: ImageVector, val route:String) {
    object FavRoutinesScreen: Screen(R.string.fav_routines, Icons.Filled.Favorite, "fav_routines_screen")
    object AllRoutinesScreen: Screen(R.string.all_routines, Icons.Filled.Edit, "all_routines_screen")
    // TODO: poner en alguna de las opciones: painterResource(id = R.drawable.baseline_fitness_center_black_36)
    object MyRoutinesScreen: Screen(R.string.my_routines, Icons.Filled.Edit, "my_routines_screen")
    object LogInScreen: Screen(R.string.log_in, Icons.Default.ArrowDropDown, "login_screen")
    object RoutineScreen: Screen(R.string.routine, Icons.Filled.Edit, "routine_screen")
    object PlayRoutineScreen: Screen(R.string.play_routine, Icons.Filled.Edit, "play_routine_screen")
}
package com.example.myapplication.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.BottomNavLayout
import com.example.myapplication.R

@Composable
fun DetailedRoutine(navController: NavController, routineId: Int, onBack: () -> Unit) {
    BottomNavLayout(navController = navController) { bottomNavPadding ->
        Scaffold(topBar = {
            RoutineTopBar(onBack)
        }, floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.PlayArrow, contentDescription = stringResource(id = R.string.play))
            }
        }, modifier = Modifier.padding(bottomNavPadding)
        ) { padding ->
            BoxWithConstraints(modifier = Modifier.padding(padding)) {
                val boxWithConstraintsScope = this
                if (maxWidth < 600.dp) {
                    RoutineDetailList(
                        padding = padding,
                        constraints = boxWithConstraintsScope
                    )
                } else {
                    Row {
                        Box(modifier = Modifier.fillMaxWidth(0.3f)) {
                            RoutineInfoCard()
                        }
                        RoutineDetailList(
                            padding = padding,
                            constraints = boxWithConstraintsScope,
                        )
                    }
                }
            }
        }
    }
}

class RoutineCycle(
    val id: Int, val title: String, val repeats: Int, val exercises: List<String>
)

fun getRoutineCycles() = (1..10).map {
    RoutineCycle(
        id = it,
        title = "Cycle $it",
        repeats = it,
        exercises = listOf("Exercise 1", "Exercise 2", "Exercise 3")
    )
}

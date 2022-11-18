package com.example.myapplication.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.BottomNavLayout
import com.example.myapplication.R
import com.example.myapplication.ScorePopup
import com.example.myapplication.ui.ExampleViewModel
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel
import com.example.myapplication.ui.screens.allroutines.hasError
import com.example.myapplication.ui.screens.favroutines.FavRoutinesViewModel

@Composable
fun DetailedRoutine(
    navController: NavController,
    mainViewModel: ExampleViewModel,
    routinesViewModel: RoutinesViewModel,
    favRoutinesViewModel: FavRoutinesViewModel,
    routineId: Int,
    onBack: () -> Unit,
    onPlay: () -> Unit
) {
    LaunchedEffect(Unit) {
        routinesViewModel.getRoutine(routineId)
    }
    LaunchedEffect(routinesViewModel.uiState.currentRoutine) {
        if (routinesViewModel.uiState.currentRoutine != null)
            routinesViewModel.isFavorite(routineId)
    }
    val routinesUiState = routinesViewModel.uiState
    val scaffoldState = rememberScaffoldState()



    var showScorePopup by remember { mutableStateOf(false) }
    val onPopupDismissed = {showScorePopup = false}
    val onRatingClick = {showScorePopup = true}

    BottomNavLayout(
        navController = navController, mainViewModel = mainViewModel
    ) { bottomNavPadding ->
        Scaffold(
            topBar = {
                RoutineTopBar(
//                    title = routinesUiState.currentRoutine?.name,
                    title = stringResource(id = R.string.routine),
                    onBack = onBack,
                    routineId = routineId,
                    routinesViewModel = routinesViewModel,
                    onRatingClick = onRatingClick
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = onPlay) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = stringResource(id = R.string.play)
                    )
                }
            },
            modifier = Modifier.padding(bottomNavPadding),
            scaffoldState = scaffoldState
        ) { padding ->
            if (routinesUiState.isFetching) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize(0.5f)
                    )
                }
                return@Scaffold
            }
            BoxWithConstraints(modifier = Modifier.padding(padding)) {
                val boxWithConstraintsScope = this
                if (maxWidth < 600.dp) {
                    routinesUiState.currentRoutine?.let {
                        RoutineDetailList(
                            padding = padding,
                            constraints = boxWithConstraintsScope,
                            routine = it
                        )
                    }
                } else {
                    Row {
                        Box(modifier = Modifier.fillMaxWidth(0.3f)) {
                            routinesUiState.currentRoutine?.let {
                                RoutineInfoCard(routine = it)
                            }
                        }
                        routinesUiState.currentRoutine?.let {
                            RoutineDetailList(
                                padding = padding,
                                constraints = boxWithConstraintsScope,
                                routine = it
                            )
                        }
                    }
                }
            }
        }
    }

    if (routinesViewModel.uiState.hasError) {
        val actionLabel = stringResource(R.string.dismiss)

        LaunchedEffect(scaffoldState.snackbarHostState) {
            val result = scaffoldState.snackbarHostState.showSnackbar(
                message = routinesViewModel.uiState.message!!,
                actionLabel = actionLabel,
            )
            when (result) {
                SnackbarResult.Dismissed -> routinesViewModel.dismissMessage()
                SnackbarResult.ActionPerformed -> routinesViewModel.dismissMessage()
            }
        }
    }

    if (showScorePopup){
        ScorePopup(onPopupDismissed = onPopupDismissed,
            routinesViewModel = routinesViewModel,
            myRoutineId = routineId)
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

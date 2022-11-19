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
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun DetailedRoutine(
    navController: NavController,
    mainViewModel: ExampleViewModel,
    routinesViewModel: RoutinesViewModel,
    routineId: Int,
    onBack: () -> Unit,
    onPlay: () -> Unit
) {
    LaunchedEffect(Unit) {
        routinesViewModel.getRoutine(routineId)
    }
    LaunchedEffect(routinesViewModel.uiState.currentRoutine) {
        if (routinesViewModel.uiState.currentRoutine != null) routinesViewModel.isFavorite(routineId)
    }
    val routinesUiState = routinesViewModel.uiState
    val scaffoldState = rememberScaffoldState()


    var showScorePopup by remember { mutableStateOf(false) }
    val onPopupDismissed: () -> Unit = { showScorePopup = false }
    val onRatingClick = { showScorePopup = true }


    BottomNavLayout(
        navController = navController, mainViewModel = mainViewModel
    ) { bottomNavPadding ->
        Scaffold(topBar = {
            RoutineTopBar(
                title = stringResource(id = R.string.routine),
                onBack = onBack,
                routinesViewModel = routinesViewModel,
                onRatingClick = onRatingClick
            )
        }, floatingActionButton = {
            FloatingActionButton(onClick = onPlay) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = stringResource(id = R.string.play)
                )
            }
        }, modifier = Modifier.padding(bottomNavPadding), scaffoldState = scaffoldState
        ) { padding ->
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = routinesUiState.isFetching),
                onRefresh = { routinesViewModel.getRoutine(routineId, true) }
            ) {
                BoxWithConstraints(modifier = Modifier.padding(padding)) {
                    val boxWithConstraintsScope = this
                    if (maxWidth < 600.dp) {
                        routinesUiState.currentRoutine?.let {
                            RoutineDetailList(
                                padding = padding,
                                constraints = boxWithConstraintsScope,
                                routinesViewModel = routinesViewModel
                            )
                        }
                    } else {
                        Row {
                            Box(modifier = Modifier.weight(1f)) {
                                routinesUiState.currentRoutine?.let {
                                    Column(
                                        modifier = Modifier.fillMaxSize().padding(start = 16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                    ) {
                                        RoutineInfoCard(routinesViewModel = routinesViewModel)
                                    }
                                }
                            }
                            Box(modifier = Modifier.weight(2f)) {
                                routinesUiState.currentRoutine?.let {
                                    RoutineDetailList(
                                        padding = padding,
                                        constraints = boxWithConstraintsScope,
                                        routinesViewModel = routinesViewModel
                                    )
                                }
                            }
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

    if (showScorePopup) {
        ScorePopup(
            onPopupDismissed = onPopupDismissed,
            routinesViewModel = routinesViewModel,
            myRoutineId = routineId
        )
    }

}

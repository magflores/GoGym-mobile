package com.example.myapplication.ui.screens.play

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.ui.screens.detail.RoutineTopBar
import com.example.myapplication.ui.state.PlayViewModel
import com.example.myapplication.ui.state.canNextExercise
import com.example.myapplication.ui.state.canPrevExercise

@Composable
fun PlayRoutine(navController: NavController, routineId: Int, onBack: () -> Unit) {
    val viewModel: PlayViewModel = viewModel()
    viewModel.getCycles()
    Scaffold(
        topBar = { RoutineTopBar(onBack) },
        bottomBar = { PlayingControls(viewModel = viewModel) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            PlayingCycles(modifier = Modifier, viewModel = viewModel)
        }
    }
}

@Composable
fun PlayingCycles(modifier: Modifier, viewModel: PlayViewModel) {
    val uiState = viewModel.uiState
    LazyColumn() {
        uiState.cycles.forEachIndexed { cycleIndex, cycle ->
            this.item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), elevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = cycle.name)
                            if (uiState.playingCycle == cycleIndex) Text(text = "THIS ONE")
                            Text(text = "${cycle.repetitions}")
                        }
                        cycle.exercises.forEachIndexed { exerciseIndex, cycleExercise ->
                            if (uiState.playingCycle == cycleIndex && uiState.playingExercise == exerciseIndex) Text(
                                text = cycleExercise.exercise.name,
                                color = Color.Red
                            )
                            else Text(text = cycleExercise.exercise.name)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PlayingControls(viewModel: PlayViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { viewModel.prevExercise() }, enabled = viewModel.uiState.canPrevExercise
        ) {
            Icon(imageVector = Icons.Filled.ChevronLeft, contentDescription = "Previous")
        }
        IconButton(
            onClick = { viewModel.nextExercise() }, enabled = viewModel.uiState.canNextExercise
        ) {
            Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = "Next")
        }
    }

}
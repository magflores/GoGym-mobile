package com.example.myapplication.ui.screens.play

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.model.RoutineCycle

@Composable
fun PlayRoutineTopBar(onBack: () -> Unit, playViewModel: PlayViewModel) {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(title = {
        Text("Routine")
    }, navigationIcon = {
        IconButton(onClick = onBack) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
    }, actions = {
        IconButton(onClick = { showMenu = !showMenu }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(id = R.string.more)
            )
        }
        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
            DropdownMenuItem(onClick = { playViewModel.changeDetailView() }) {
                if (playViewModel.uiState.detailed) Text(text = stringResource(R.string.summaryView))
                else Text(text = stringResource(R.string.detailedView))
            }
        }
    })
}

@Composable
fun PlayRoutine(
    navController: NavController, routineId: Int, onBack: () -> Unit, viewModel: PlayViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.getCycles(routineId)
    }
    Scaffold(topBar = { PlayRoutineTopBar(onBack = onBack, playViewModel = viewModel) },
        bottomBar = { PlayingControls(viewModel = viewModel) }) { padding ->
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
                if (uiState.detailed) {
                    DetailedCard(
                        cycle = cycle, cycleIndex = cycleIndex, playViewModel = viewModel
                    )
                } else {
                    BriefCard(cycle = cycle, cycleIndex = cycleIndex, playViewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun DetailedCard(
    modifier: Modifier = Modifier,
    cycle: RoutineCycle,
    cycleIndex: Int,
    playViewModel: PlayViewModel
) {
    val uiState = playViewModel.uiState
    val isSelectedCycle = (uiState.playingCycle == cycleIndex)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .alpha(if (isSelectedCycle) 1.0f else ContentAlpha.disabled), elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = cycle.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = "${cycle.repetitions}")
            }
            cycle.exercises.forEachIndexed { exerciseIndex, cycleExercise ->
                Divider(modifier = Modifier.alpha(if (exerciseIndex == 0) 1.0f else 0.75f))
                val isSelectedExercise = isSelectedCycle && exerciseIndex == uiState.playingExercise
                val textColor = if (isSelectedExercise) Color.Black
                else Color.Black.copy(alpha = ContentAlpha.disabled)
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = cycleExercise.name, color = textColor)
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(text = cycleExercise.duration.toString(), color = textColor)
                            Text(text = cycleExercise.repetitions.toString(), color = textColor)
                        }
                    }
                    Text(text = cycleExercise.detail)
                }
            }
        }
    }
}

@Composable
fun BriefCard(
    modifier: Modifier = Modifier,
    cycle: RoutineCycle,
    cycleIndex: Int,
    playViewModel: PlayViewModel
) {
    val uiState = playViewModel.uiState
    val isSelectedCycle = (uiState.playingCycle == cycleIndex)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .alpha(if (isSelectedCycle) 1.0f else ContentAlpha.disabled), elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = cycle.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = "${cycle.repetitions}")
            }
            Divider()
            cycle.exercises.forEachIndexed { exerciseIndex, cycleExercise ->
                val isSelectedExercise = isSelectedCycle && exerciseIndex == uiState.playingExercise
                val textColor = if (isSelectedExercise) Color.Black
                else Color.Black.copy(alpha = ContentAlpha.disabled)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = cycleExercise.name, color = textColor)
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = cycleExercise.duration.toString(), color = textColor)
                        Text(text = cycleExercise.repetitions.toString(), color = textColor)
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
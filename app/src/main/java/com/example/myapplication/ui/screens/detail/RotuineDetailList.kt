package com.example.myapplication.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.model.RoutineCycle
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel


@Composable
fun RoutineDetailList(
    padding: PaddingValues,
    constraints: BoxWithConstraintsScope,
    routinesViewModel: RoutinesViewModel
) {
    val routine = routinesViewModel.uiState.currentRoutine
    routine?.let {

        LazyColumn(
            modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            if (constraints.maxWidth < 600.dp) {
                this.item {
                    RoutineInfoCard(
                        modifier = Modifier.padding(padding), routinesViewModel = routinesViewModel
                    )
                }
            }
            this.item {
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .padding(padding)
                )
            }
            routine.cycles?.let { cycles ->
                this.items(items = cycles) {
                    RoutineCycleCard(it)
                }
            }
        }
    }
}

@Composable
fun RoutineCycleCard(cycle: RoutineCycle) {
    Card(
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "${cycle.name} Repeat: ${cycle.repetitions}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${cycle.repetitions}", fontSize = 16.sp
            )
            for (exercise in cycle.exercises) {
                Text(
                    text = exercise.name, fontSize = 16.sp
                )
            }
        }
    }
}

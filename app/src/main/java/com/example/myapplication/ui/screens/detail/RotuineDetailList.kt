package com.example.myapplication.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RoutineDetailList(padding: PaddingValues, constraints : BoxWithConstraintsScope) {
    LazyColumn(
        modifier = Modifier.padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        if (constraints.maxWidth < 600.dp) {
            this.item { RoutineInfoCard(modifier = Modifier.padding(padding)) }
        }
        this.item {
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .padding(padding)
            )
        }
        this.items(items = getRoutineCycles()) {
            RoutineCycleCard(it)
        }
    }
}

@Composable
fun RoutineCycleCard(cycle: RoutineCycle) {
    Card(
        backgroundColor = Color.LightGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "${cycle.title} Repeat: ${cycle.repeats}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${cycle.repeats}", fontSize = 16.sp
            )
            for (exercise in cycle.exercises) {
                Text(
                    text = exercise, fontSize = 16.sp
                )
            }
        }
    }
}

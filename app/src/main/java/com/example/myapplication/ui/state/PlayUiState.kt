package com.example.myapplication.ui.state

import com.example.myapplication.RoutineCycle

data class PlayUiState(
    val playingCycle: Int = 0,
    val playingExercise: Int = 0,
    val simplistic: Boolean = false,
    val cycles: List<RoutineCycle> = listOf(),
)

val PlayUiState.canNextExercise: Boolean get() = ! (playingCycle == cycles.size-1 && playingExercise == cycles[playingCycle].exercises.size-1)

val PlayUiState.canPrevExercise: Boolean get() = ! (playingCycle == 0 && playingExercise == 0)

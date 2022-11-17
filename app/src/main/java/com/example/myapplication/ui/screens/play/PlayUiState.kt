package com.example.myapplication.ui.screens.play

import com.example.myapplication.data.model.RoutineCycle

data class PlayUiState(
    val playingCycle: Int = 0,
    val playingExercise: Int = 0,
    val currentRepeat: Int = 0,
    val simplistic: Boolean = false,
    val cycles: List<RoutineCycle> = listOf(),
    val isFetching: Boolean = false,
    val message: String? = null,
)

val PlayUiState.canNextExercise: Boolean get() = ! (playingCycle == cycles.size-1 && currentRepeat == cycles[playingCycle].repetitions-1 && playingExercise == cycles[playingCycle].exercises.size-1)

val PlayUiState.canPrevExercise: Boolean get() = ! (playingCycle == 0 && currentRepeat == 0 && playingExercise == 0)

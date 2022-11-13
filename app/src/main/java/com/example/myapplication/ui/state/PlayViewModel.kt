package com.example.myapplication.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.*

class PlayViewModel() : ViewModel() {
    var uiState by mutableStateOf(PlayUiState())
        private set

    fun getCycles() {
        uiState = uiState.copy(
            cycles = (1..10).map {
                RoutineCycle(
                    id = it,
                    name = "cycle $it",
                    detail = "detail $it",
                    type = CycleType.WARMUP,
                    repetitions = it,
                    exercises = (1..it).map { exercise ->
                        CycleExercise(
                            order = exercise,
                            duration = exercise * 30,
                            repetitions = exercise * 2,
                            exercise = Exercise(
                                id = exercise,
                                name = "Exercise $exercise",
                                detail = "Detail exercise $exercise",
                                type = ExerciseType.EXERCISE,
                                date = 0,
                                metadata = ""
                            )
                        )
                    },
                    metadata = ""
                )
            }
        )
    }

    /*Deberia tener acceso a la rutina, y asi cuando se llega al ultimo ejercicio
    * de un ciclo pasar directo al siguiente*/
    fun nextExercise() {
        if (uiState.playingExercise >= uiState.cycles[uiState.playingCycle].exercises.size - 1) {
            nextCycle()
            uiState = uiState.copy(playingExercise = 0)
        } else
            uiState = uiState.copy(playingExercise = uiState.playingExercise + 1)
    }

    fun prevExercise() {
        if (uiState.playingExercise == 0) {
            prevCycle()
            uiState =
                uiState.copy(playingExercise = uiState.cycles[uiState.playingCycle].exercises.size - 1)
        } else
            uiState = uiState.copy(playingExercise = uiState.playingExercise - 1)
    }

    fun nextCycle() {
        uiState = uiState.copy(playingCycle = uiState.playingCycle + 1)
    }

    fun prevCycle() {
        uiState = uiState.copy(playingCycle = uiState.playingCycle - 1)
    }

    fun reset() {
        uiState = PlayUiState()
    }
}
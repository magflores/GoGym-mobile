package com.example.myapplication.ui.screens.play

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.RoutineRepository
import kotlinx.coroutines.launch

class PlayViewModel(
    private val routineRepository: RoutineRepository,
) : ViewModel() {
    var uiState by mutableStateOf(PlayUiState())
        private set



    fun getCycles(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        kotlin.runCatching {
            routineRepository.getRoutine(routineId, false)
        }.onSuccess {
            uiState = uiState.copy(
                isFetching = false,
                playingCycle = 0,
                playingExercise = 0,
                currentRepeat = 0,
                cycles = it.cycles ?: emptyList()
            )
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message, isFetching = false
            )
        }
    }

    /*Deberia tener acceso a la rutina, y asi cuando se llega al ultimo ejercicio
    * de un ciclo pasar directo al siguiente*/
    fun nextExercise() {
        if (uiState.playingExercise >= uiState.cycles[uiState.playingCycle].exercises.size - 1) {
            nextCycle()
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
        if (uiState.currentRepeat >= uiState.cycles[uiState.playingCycle].repetitions - 1) {
            uiState = uiState.copy(
                playingCycle = uiState.playingCycle + 1,
                playingExercise = 0,
                currentRepeat = 0,
            )
        } else {
            uiState = uiState.copy(
                playingExercise = 0,
                currentRepeat = uiState.currentRepeat + 1,
            )
        }
    }

    fun prevCycle() {
        if (uiState.currentRepeat == 0) {
            uiState = uiState.copy(
                playingCycle = uiState.playingCycle - 1,
                playingExercise = uiState.cycles[uiState.playingCycle].exercises.size - 1,
                currentRepeat = uiState.cycles[uiState.playingCycle-1].repetitions - 1
            )
        } else {
            uiState = uiState.copy(
                playingCycle = uiState.playingCycle,
                playingExercise = uiState.cycles[uiState.playingCycle].exercises.size - 1,
                currentRepeat = uiState.currentRepeat - 1
            )
        }
    }

    fun reset() {
        uiState = PlayUiState()
    }

    fun changeDetailView() {
        uiState = uiState.copy(
            detailed = !uiState.detailed
        )
    }
}
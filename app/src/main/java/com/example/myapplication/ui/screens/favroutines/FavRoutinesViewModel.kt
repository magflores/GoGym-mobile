package com.example.myapplication.ui.screens.favroutines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.RoutineRepository
import kotlinx.coroutines.launch

class FavRoutinesViewModel(
    private val routineRepository: RoutineRepository,
) : ViewModel() {
    var uiState by mutableStateOf(FavRoutinesUiState())
        private set

    fun getFavourites() = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, message = null)
        kotlin.runCatching {
            routineRepository.getFavs()
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false, favourites = response, success = true
            )
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message, isFetching = false
            )
        }
    }
}
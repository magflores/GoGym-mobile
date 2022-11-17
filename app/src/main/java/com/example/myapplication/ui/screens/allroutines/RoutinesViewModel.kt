package com.example.myapplication.ui.screens.allroutines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.RoutineRepository
import kotlinx.coroutines.launch

class RoutinesViewModel(
    private val routineRepository: RoutineRepository,
) : ViewModel() {
    var uiState by mutableStateOf(RoutinesUiState())
        private set

    fun getAllRoutines() = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, message = null)
        kotlin.runCatching {
            routineRepository.getAllRoutines(
                refresh = false, orderBy = uiState.orderBy.order, sort = uiState.sort.sort
            )
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false, routines = response
            )
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message, isFetching = false
            )
        }
    }

    fun searchRoutines(search: String) = viewModelScope.launch {
        if (search.length < 3) uiState =
            uiState.copy(message = "Must contain more than three characters")
        else {
            uiState = uiState.copy(isFetching = true, message = null)
            kotlin.runCatching {
                routineRepository.searchRoutines(
                    search = search, orderBy = uiState.orderBy.order, sort = uiState.sort.sort
                )
            }.onSuccess { response ->
                uiState = uiState.copy(
                    isFetching = false, routines = response
                )
            }.onFailure { e ->
                uiState = uiState.copy(
                    message = e.message, isFetching = false
                )
            }
        }
    }

    fun getUserRoutines(userId: Int) = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, message = null)
        kotlin.runCatching {
            routineRepository.getUserRoutines(
                userId = userId,
                orderBy = uiState.orderBy.order,
                sort = uiState.sort.sort
            )
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false, routines = response
            )
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message, isFetching = false
            )
        }
    }
}

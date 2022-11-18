package com.example.myapplication.ui.screens.favroutines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.repository.RoutineRepository
import kotlinx.coroutines.launch

class FavRoutinesViewModel(
    private val routineRepository: RoutineRepository,
) : ViewModel() {
    var uiState by mutableStateOf(FavRoutinesUiState())
        private set

    fun toggleView() {
        uiState = uiState.copy(
            typeOfView_List_Grid = !uiState.typeOfView_List_Grid
        )
    }

    fun getFavourites(refresh: Boolean = false) = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, message = null)
        kotlin.runCatching {
            routineRepository.getFavs(refresh)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false, favourites = response
            )
        }.onFailure { e ->
            if (e.message != BuildConfig.API_UNAUTHORIZED_MESSAGE)
                uiState = uiState.copy(
                    message = e.message, isFetching = false
                )
        }
    }

    fun dismissMessage() {
        uiState = uiState.copy(message = null)
    }
}
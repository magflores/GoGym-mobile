package com.example.myapplication.ui.screens.allroutines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.repository.RoutineRepository
import kotlinx.coroutines.launch

class RoutinesViewModel(
    private val routineRepository: RoutineRepository
) : ViewModel() {
    var uiState by mutableStateOf(RoutinesUiState())
        private set

    fun toggleView() {
        uiState = uiState.copy(
            typeOfView_List_Grid = !uiState.typeOfView_List_Grid
        )
    }

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
            if (e.message != BuildConfig.API_UNAUTHORIZED_MESSAGE)
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
                if (e.message != BuildConfig.API_UNAUTHORIZED_MESSAGE)
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
            if (e.message != BuildConfig.API_UNAUTHORIZED_MESSAGE)
                uiState = uiState.copy(
                    message = e.message, isFetching = false
                )
        }
    }

    fun getRoutine(routineId: Int, refresh: Boolean = false) = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, message = null)
        kotlin.runCatching {
            routineRepository.getRoutine(
                routineId = routineId,
                refresh = refresh
            )
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false, currentRoutine = response
            )
        }.onFailure { e ->
            if (e.message != BuildConfig.API_UNAUTHORIZED_MESSAGE)
                uiState = uiState.copy(
                    message = e.message, isFetching = false
                )
        }
    }

    fun isFavorite(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, message = null)
        kotlin.runCatching {
            routineRepository.isFavorite(routineId)
        }.onSuccess {
            var copyRoutine = uiState.currentRoutine
            copyRoutine = copyRoutine?.copy(isFavorite = it)
            uiState = uiState.copy(
                isFetching = false,
                currentRoutine = copyRoutine
            )
        }.onFailure { e ->
            if (e.message != BuildConfig.API_UNAUTHORIZED_MESSAGE)
                uiState = uiState.copy(
                    message = e.message, isFetching = false
                )
        }
    }

    fun setScore(routineId: Int, score: Int, review: String) = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, message = null)
        kotlin.runCatching {
            routineRepository.setScore(routineId, score, review)
        }.onSuccess {
            uiState = uiState.copy(
                isFetching = false
            )
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message, isFetching = false
            )

        }

    }

    fun markFavourite(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, message = null)
        var fav = false
        kotlin.runCatching {
            val newRoutineToAdd = routineRepository.getRoutine(routineId)
            if (newRoutineToAdd.id?.let { routineRepository.isFavorite(it) } == true) {
                fav = true
                routineRepository.deleteToFavourite(newRoutineToAdd.id)
            } else
                newRoutineToAdd.id?.let { routineRepository.addToFavourite(it) }
        }.onSuccess {
            var copyRoutine = uiState.currentRoutine
            copyRoutine = copyRoutine?.copy(isFavorite = fav)
            uiState = uiState.copy(
                isFetching = false, currentRoutine = copyRoutine
            )
            getRoutine(routineId)
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

    fun orderBy(order: Order) {
        uiState = uiState.copy(orderBy = order)
    }
}


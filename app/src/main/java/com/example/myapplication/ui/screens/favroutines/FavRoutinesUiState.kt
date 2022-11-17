package com.example.myapplication.ui.screens.favroutines

import com.example.myapplication.data.model.Routine

data class FavRoutinesUiState(
    val isFetching: Boolean = false,
    val favourites: List<Routine>? = null,
    val message: String? = null
)

val FavRoutinesUiState.hasError: Boolean get() = message != null
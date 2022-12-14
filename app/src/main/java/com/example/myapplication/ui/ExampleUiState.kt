package com.example.myapplication.ui

import com.example.myapplication.data.model.User
import com.example.myapplication.ui.screens.favroutines.FavRoutinesUiState

data class ExampleUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val message: String? = null,
    val typeOfView_List_Grid: Boolean = true
)

val ExampleUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val ExampleUiState.hasError: Boolean get() = message != null
val ExampleUiState.stateTypeOfView_List_Grid: Boolean get() = typeOfView_List_Grid
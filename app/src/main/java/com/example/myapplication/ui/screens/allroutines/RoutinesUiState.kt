package com.example.myapplication.ui.screens.allroutines

import com.example.myapplication.data.model.Routine
import com.example.myapplication.ui.ExampleUiState

enum class Sort(val sort: String) {
    ASC("asc"),
    DESC("desc")
}

enum class Order(val order: String) {
    ID("id"),
    NAME("name"),
    DETAIL("detail"),
    DATE("date"),
    SCORE("score"),
    DIFFICULTY("difficulty"),
    CATEGORY("category"),
    USER("user")
}

data class RoutinesUiState(
    val isFetching: Boolean = false,
    val message: String? = null,
    val routines: List<Routine> = emptyList(),
    val currentRoutine: Routine? = null,
    val orderBy: Order = Order.DATE,
    val sort: Sort = Sort.ASC,
    val typeOfView_List_Grid: Boolean = true
)

val RoutinesUiState.stateTypeOfView_List_Grid: Boolean get() = typeOfView_List_Grid

val RoutinesUiState.hasError: Boolean get() = message != null

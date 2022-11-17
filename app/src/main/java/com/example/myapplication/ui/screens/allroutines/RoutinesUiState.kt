package com.example.myapplication.ui.screens.allroutines

import com.example.myapplication.data.model.Routine

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
    val orderBy: Order = Order.DATE,
    val sort: Sort = Sort.ASC,
)
package com.example.myapplication.data.model

import java.util.*

data class Routine(
    val id: Int?,
    val name: String,
    val detail: String,
    val date: Date,
    val score: Int,
    val isPublic: Boolean,
    val difficulty: String,
    val user: RoutineUser,
    val category: String,
    val cycles: List<RoutineCycle>?,
    val metadata: String,
    val isFavorite: Boolean = false,
)
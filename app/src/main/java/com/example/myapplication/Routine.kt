package com.example.myapplication

import com.example.myapplication.data.model.User

enum class RoutineDifficulty {
    ROOKIE,
}

class RoutineCategory(
    val id: Int,
    val name: String,
    val detail: String,
)

class Routine(
    val id: Int,
    val name: String,
    val detail: String,
    val date: Int,
    val score: Int,
    val isPublic: Boolean,
    val difficulty: RoutineDifficulty,
    val user: User,
    val category: RoutineCategory,
    val cycles: List<RoutineCycle>,
    val metadata: Metadata,
)
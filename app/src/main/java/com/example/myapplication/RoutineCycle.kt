package com.example.myapplication

enum class CycleType {
    WARMUP,
}

class RoutineCycle(
    val id: Int,
    val name: String,
    val detail: String,
    val type: CycleType,
    val repetitions: Int,
    val exercises: List<CycleExercise>,
    val metadata: String,
)
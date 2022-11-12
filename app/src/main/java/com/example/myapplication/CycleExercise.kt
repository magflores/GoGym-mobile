package com.example.myapplication

enum class ExerciseType {
    EXERCISE,

}

class Exercise(
    val id: Int,
    val name: String,
    val detail: String,
    val type: ExerciseType,
    val date: Int,
    val metadata: String,
)

class CycleExercise(
    val order: Int,
    val duration: Int,
    val repetitions: Int,
    val exercise: Exercise
)
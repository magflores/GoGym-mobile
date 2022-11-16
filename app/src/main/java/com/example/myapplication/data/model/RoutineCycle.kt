package com.example.myapplication.data.model


data class RoutineCycle(
    var id: Int,
    var name: String,
    var detail: String,
    var type: String,
    var order: Int,
    var repetitions: Int,
    var exercises: List<CycleExercise>
)
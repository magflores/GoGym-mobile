package com.example.myapplication.data.model

import java.util.Date

data class CycleExercise(
    val order: Int,
    val duration: Int,
    val repetitions: Int,
    val id: Int,
    val detail: String,
    val type: String,
    val date: Date,
    val metadata: String? = null,
)
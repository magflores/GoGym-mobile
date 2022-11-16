package com.example.myapplication.data.model

import java.util.Date

data class RoutineUser(
    var id: Int,
    var username: String,
    var gender: String,
    var avatarUrl: String,
    var date: Date,
    var lastActivity: Date
)
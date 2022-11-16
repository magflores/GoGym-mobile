package com.example.myapplication.data.network.model

import com.example.myapplication.data.model.RoutineUser
import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkUserRoutines(
//    tambien lo uso en getExecutionById
    @SerializedName("id") var id: Int,
    @SerializedName("username") var username: String,
    @SerializedName("gender") var gender: String,
    @SerializedName("avatarUrl") var avatarUrl: String,
    @SerializedName("date") var date: Date,
    @SerializedName("lastActivity") var lastActivity: Date
) {
    fun asModel() : RoutineUser {
        return RoutineUser(
            id = id,
            username = username,
            gender = gender,
            avatarUrl = avatarUrl,
            date = date,
            lastActivity = lastActivity
        )
    }
}
package com.example.myapplication.data.network.model

import com.example.myapplication.data.model.Routine
import com.google.gson.annotations.SerializedName
import java.util.*

//import com.example.myapplication.data.network.model.NetworkPagedContent

data class NetworkRoutines(
//    esta clase es la de NetworkPagedContent, tienen los mismos elementos,
//    la dejo asi para no equivocarme y hacer 2 clases iguales,
//    esto lo pense cuando estaba haciendo la interfaz de RoutinesService

//    son los datos para la rutina, me parece

//    tambien es la respuesta que obtenemos despues de crear una nueva rutina

//    tambien lo uso en getExecutionById

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String,
    @SerializedName("detail") var detail: String,
    @SerializedName("date") var date: Date,
    @SerializedName("score") var score: Int,
    @SerializedName("isPublic") var isPublic: Boolean,
    @SerializedName("difficulty") var difficulty: String,
    @SerializedName("user") var user: NetworkUserRoutines,
    @SerializedName("category") var category: NetworkRoutineCategory?,
    @SerializedName("metadata") var metadata: NetworkUserMetadata
) {
    fun asModel(): Routine {
        return Routine(
            id = id,
            name = name,
            detail = detail,
            date = date,
            score = score,
            isPublic = isPublic,
            difficulty = difficulty,
            user = user.asModel(),
            category = category?.asModel() ?: "",
            cycles = null,
            metadata = "",
        )
    }
}
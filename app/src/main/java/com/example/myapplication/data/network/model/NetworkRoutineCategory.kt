package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkRoutineCategory(
//    esta dentro de NetworkRoutines, es una categorya que necesita

//    tambien lo uso en getExecutionById
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("detail") var detail: String
)
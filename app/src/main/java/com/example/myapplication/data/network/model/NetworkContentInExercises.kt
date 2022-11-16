package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkContentInExercises(
//    es el contenido de la respuesta de getExercises!!!
    @SerializedName("id"       ) var id       : Int?    = null,
    @SerializedName("name"     ) var name     : String? = null,
    @SerializedName("detail"   ) var detail   : String? = null,
    @SerializedName("type"     ) var type     : String? = null,
    @SerializedName("date"     ) var date     : Date?   = null,
    @SerializedName("metadata" ) var metadata : String? = null
)

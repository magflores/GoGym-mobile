package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

//import com.example.myapplication.data.network.model.NetworkPagedContent

data class NetworkRoutines (
//    esta clase es la de NetworkPagedContent, tienen los mismos elementos,
//    la dejo asi para no equivocarme y hacer 2 clases iguales,
//    esto lo pense cuando estaba haciendo la interfaz de RoutinesService

//    son los datos para la rutina, me parece

//    tambien es la respuesta que obtenemos despues de crear una nueva rutina

//    tambien lo uso en getExecutionById

    @SerializedName("id"         ) var id         : Int?                     = null,
    @SerializedName("name"       ) var name       : String?                  = null,
    @SerializedName("detail"     ) var detail     : String?                  = null,
    @SerializedName("date"       ) var date       : Date?                    = null,
    @SerializedName("score"      ) var score      : Int?                     = null,
    @SerializedName("isPublic"   ) var isPublic   : Boolean?                 = null,
    @SerializedName("difficulty" ) var difficulty : String?                  = null,
    @SerializedName("user"       ) var user       : NetworkUserRoutines?     = NetworkUserRoutines(),
    @SerializedName("category"   ) var category   : NetworkRoutineCategory?  = NetworkRoutineCategory(),
    @SerializedName("metadata"   ) var metadata   : NetworkUserMetadata?     = null
//    TODO: chequear el Metadata!

)
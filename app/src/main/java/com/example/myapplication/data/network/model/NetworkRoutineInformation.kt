package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkRoutineInformation(
//    creo esta pero todos los datos tambien estan en: NetworkRoutines,
//    estan pero este es cuado agregamos una nueva,
//    le puse el mismo nombre que esta en la api (add routine)

    @SerializedName("name"       ) var name       : String?                 = null,
    @SerializedName("detail"     ) var detail     : String?                 = null,
    @SerializedName("isPublic"   ) var isPublic   : Boolean?                = null,
    @SerializedName("difficulty" ) var difficulty : String?                 = null,
    @SerializedName("category"   ) var category   : NetworkRoutineCategory? = NetworkRoutineCategory(),
//    en este de category solo necesitaria el id segun la pagina que los convierte, uso este que ya esta
    @SerializedName("metadata"   ) var metadata   : NetworkUserMetadata?    = null
)

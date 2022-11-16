package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkRoutineInformation(
//    creo esta pero todos los datos tambien estan en: NetworkRoutines,
//    estan pero este es cuado agregamos una nueva,
//    le puse el mismo nombre que esta en la api (add routine)

    @SerializedName("name") var name: String,
    @SerializedName("detail") var detail: String,
    @SerializedName("isPublic") var isPublic: Boolean?,
    @SerializedName("difficulty") var difficulty: String,
    @SerializedName("category") var category: NetworkRoutineCategory,
//    en este de category solo necesitaria el id segun la pagina que los convierte, uso este que ya esta
    @SerializedName("metadata") var metadata: NetworkUserMetadata? = null
)

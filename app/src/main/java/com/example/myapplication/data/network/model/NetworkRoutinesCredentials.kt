package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkRoutinesCredentials (
//    los datos que le podemos pasar para pedir todas las rutinas o algunas

    @SerializedName("categoryId" ) var categoryId : Int?    = null,
    @SerializedName("userId"     ) var userId     : Int?    = null,
    @SerializedName("difficulty" ) var difficulty : String? = null,
    @SerializedName("score"      ) var score      : Int?    = null,
    @SerializedName("search"     ) var search     : String? = null,
    @SerializedName("page"       ) var page       : Int?    = null,
    @SerializedName("size"       ) var size       : Int?    = null,
    @SerializedName("orderBy"    ) var orderBy    : String? = null,
    @SerializedName("direction"  ) var direction  : String? = null,

)
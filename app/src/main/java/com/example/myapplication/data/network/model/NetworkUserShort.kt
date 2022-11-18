package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkUserShort(
//    TODO: chequear si no esta repetido
    @SerializedName("id"           ) var id           : Int,
    @SerializedName("username"     ) var username     : String,
    @SerializedName("gender"       ) var gender       : String? = null,
    @SerializedName("avatarUrl"    ) var avatarUrl    : String? = null,
    @SerializedName("created"      ) var created      : Int?    = null,
    @SerializedName("lastActivity" ) var lastActivity : Int?    = null

)

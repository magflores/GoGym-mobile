package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

class NetworkUser (
    @SerializedName("id"           ) var id           : Int?    = null,
    @SerializedName("username"     ) var username     : String? = null,
    @SerializedName("gender"       ) var gender       : String? = null,
    @SerializedName("avatarUrl"    ) var avatarUrl    : String? = null,
    @SerializedName("date"         ) var date         : Int?    = null,
    @SerializedName("lastActivity" ) var lastActivity : Int?    = null


)
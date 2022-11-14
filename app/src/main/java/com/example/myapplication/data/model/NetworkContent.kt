package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

class NetworkContent (

    @SerializedName("id"         ) var id         : Int?      = null,
    @SerializedName("name"       ) var name       : String?   = null,
    @SerializedName("detail"     ) var detail     : String?   = null,
    @SerializedName("created"    ) var created    : Int?      = null,
    @SerializedName("score"      ) var score      : Int?      = null,
    @SerializedName("isPublic"   ) var isPublic   : Boolean?  = null,
    @SerializedName("difficulty" ) var difficulty : String?   = null,
    @SerializedName("user"       ) var user       : NetworkUser?     = NetworkUser(),
    @SerializedName("category"   ) var category   : NetworkCategory? = NetworkCategory()
)
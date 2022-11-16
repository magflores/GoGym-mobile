package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkExercise(
    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("name"   ) var name   : String? = null,
    @SerializedName("detail" ) var detail : String? = null,
    @SerializedName("type"   ) var type   : String? = null,
    @SerializedName("date"   ) var date   : Date?   = null,
    @SerializedName("order"  ) var order  : Int?    = null,

//    le agrego esto del metadata para getCycleExercises, ver de sino hacer una data class nueva
    @SerializedName("metadata"    ) var metadata    : NetworkUserMetadata? = null

)

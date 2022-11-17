package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkExercise(
    @SerializedName("id"     ) var id     : Int,
    @SerializedName("name"   ) var name   : String,
    @SerializedName("detail" ) var detail : String,
    @SerializedName("type"   ) var type   : String,
    @SerializedName("date"   ) var date   : Date,

//    le agrego esto del metadata para getCycleExercises, ver de sino hacer una data class nueva
    @SerializedName("metadata"    ) var metadata    : NetworkUserMetadata? = null

) {

}

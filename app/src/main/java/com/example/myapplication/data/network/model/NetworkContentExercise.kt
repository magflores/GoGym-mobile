package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkContentExercise(
//    lo uso para la respuesta de getCycleExercises (sobra el metadata, pero como puede
//    ser null estamos bien)
    @SerializedName("exercise"    ) var exercise    : NetworkExercise?     = NetworkExercise(),
    @SerializedName("order"       ) var order       : Int?                 = null,
    @SerializedName("duration"    ) var duration    : Int?                 = null,
    @SerializedName("repetitions" ) var repetitions : Int?                 = null,
    @SerializedName("metadata"    ) var metadata    : NetworkUserMetadata? = null
)

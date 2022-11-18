package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkReviewContent(
    @SerializedName("id"      ) var id      : Int,
    @SerializedName("date"    ) var date    : Date?     = null,
    @SerializedName("score"   ) var score   : Int,
    @SerializedName("review"  ) var review  : String?  = null,
    @SerializedName("routine" ) var routine : NetworkRoutines? = null
)

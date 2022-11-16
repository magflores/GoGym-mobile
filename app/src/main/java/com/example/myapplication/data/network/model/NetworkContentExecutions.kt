package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkContentExecutions(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("date") var date: Date? = null,
    @SerializedName("duration") var duration: Int? = null,
    @SerializedName("wasModified") var wasModified: Boolean? = null,
    @SerializedName("routine") var routine: NetworkRoutines? = NetworkRoutines()
)

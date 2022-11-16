package com.example.myapplication.data.network.model

import com.example.myapplication.data.model.RoutineCycle
import com.google.gson.annotations.SerializedName

data class NetworkRoutineCycle(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("detail") var detail: String,
    @SerializedName("type") var type: String,
    @SerializedName("order") var order: Int,
    @SerializedName("repetitions") var repetitions: Int,
    @SerializedName("metadata") var metadata: NetworkUserMetadata? = null,
) {
    fun asModel() : RoutineCycle {
        return RoutineCycle(
            id = id,
            name = name,
            detail = detail,
            type = type,
            order = order,
            repetitions = repetitions,
            exercises = listOf()
        )
    }
}

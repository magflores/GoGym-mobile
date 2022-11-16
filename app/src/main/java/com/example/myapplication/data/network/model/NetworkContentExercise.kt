package com.example.myapplication.data.network.model

import com.example.myapplication.data.model.CycleExercise
import com.google.gson.annotations.SerializedName

data class NetworkContentExercise(
//    lo uso para la respuesta de getCycleExercises (sobra el metadata, pero como puede
//    ser null estamos bien)
    @SerializedName("exercise") var exercise: NetworkExercise,
    @SerializedName("order") var order: Int,
    @SerializedName("duration") var duration: Int,
    @SerializedName("repetitions") var repetitions: Int,
    @SerializedName("metadata") var metadata: NetworkUserMetadata
) {
    fun asModel(): CycleExercise {
        return CycleExercise(
            order = order,
            duration = duration,
            repetitions = repetitions,
            id = exercise.id,
            detail = exercise.detail,
            type = exercise.type,
            date = exercise.date,
        )
    }
}

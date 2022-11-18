package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkReviewInformation(

    @SerializedName("score"    ) var score    : Int,
    @SerializedName("password" ) var password : String
)

package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName


data class NetworkFavourites (

    @SerializedName("totalCount" ) var totalCount : Int?               = null,
    @SerializedName("orderBy"    ) var orderBy    : String?            = null,
    @SerializedName("direction"  ) var direction  : String?            = null,
    @SerializedName("content"    ) var content    : ArrayList<NetworkContent> = arrayListOf(),
    @SerializedName("size"       ) var size       : Int?               = null,
    @SerializedName("page"       ) var page       : Int?               = null,
    @SerializedName("isLastPage" ) var isLastPage : Boolean?           = null

)
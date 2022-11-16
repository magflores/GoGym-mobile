package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkPagedContent<T> (

    @SerializedName("totalCount")
    var totalCount: Int,
    @SerializedName("orderBy")
    var orderBy: String? = null,
    @SerializedName("direction")
    var direction: String? = null,
    @SerializedName("content")
    var content : List<T> = arrayListOf(), // creo que usa un listOf() aca, en vez de un arrayListOf()
    @SerializedName("size")
    var size : Int,
    @SerializedName("page")
    var page : Int,
    @SerializedName("isLastPage")
    var isLastPage : Boolean

)
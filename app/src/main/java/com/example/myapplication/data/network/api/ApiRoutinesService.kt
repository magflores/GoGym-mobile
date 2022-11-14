package com.example.myapplication.data.network.api

import com.example.myapplication.data.model.NetworkFavourites
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRoutinesService {
    @GET("favourites")
    suspend fun favourites(@Query("page") page: Int,@Query("size") size: Int) : Response<NetworkFavourites>


}
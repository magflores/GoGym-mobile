package com.example.myapplication.data.network.api

import com.example.myapplication.data.model.NetworkFavourites
import com.google.gson.annotations.Until
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRoutinesService {
    @GET("favourites")
    suspend fun favourites(@Query("page") page: Int, @Query("size") size: Int) : Response<NetworkFavourites>

    @POST("favourites/{routineID}")
    suspend fun addFavourites(@Path("routineID") routineID: Int) : Response<Unit>

    @POST("favourites/{routineID}")
    suspend fun removeFavourites(@Path("routineID") routineID: Int) : Response<Unit>

}
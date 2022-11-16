package com.example.myapplication.data.network.api

import com.example.myapplication.data.network.model.*
import retrofit2.Response
import retrofit2.http.*
import java.security.cert.CertStoreSpi

interface ApiRoutinesService {
    //    TODO: chequear si necesitamos todas las implementaciones!!!!

    @GET("routines") // TODO: chequear lo de routinesCredentials
    suspend fun getRoutines(
        @Query("routinesCredentials") routinesCredentials: NetworkRoutinesCredentials) :
            Response<NetworkPagedContent<NetworkRoutines>>

//    no hace falta implementarlo
//    @POST
//    suspend fun addRoutine(@Body routinesCredentials: NetworkRoutinesCredentials) : Response<NetworkRoutines>

    @GET("routines/{routineId}/cycles")
    suspend fun getRoutineCycles(@Path("routineId") routineId: Int, @Query("page") page: Int,
                                  @Query("size") size: Int, @Query("orderBy") orderBy: String,
                                  @Query("direction") direction: String) :
            Response<NetworkPagedContent<NetworkRoutineCycle>>

    @GET("routines/{routineId}/cycles/{cycleId}")
    suspend fun getRoutineCyclesById(@Path("routineId") routineId: Int,
                                     @Path("cycleId") cycleId: Int) : Response<NetworkRoutineCycle>

    @GET("cycles/{cycleId}/exercises")
    suspend fun getCycleExercises(@Path("cycleId") cycleId: Int, @Query("page") page: Int,
                                  @Query("size") size: Int, @Query("orderBy") orderBy: String,
                                  @Query("direction") direction: String) :
            Response<NetworkPagedContent<NetworkContentExercise>>

    @GET("cycles/{cycleId}/exercises/{exerciseId}")
    suspend fun getCycleExercisesById(@Path("cycleId") cycleId: Int, @Path("exerciseId") exerciseId: Int) :
            Response<NetworkContentExercise>

    @GET("exercises")
    suspend fun getExercises(@Query("search") search: String, @Query("page") page: Int,
                             @Query("size") size: Int, @Query("orderBy") orderBy: String,
                             @Query("direction") direction: String) :
            Response<NetworkPagedContent<NetworkContentInExercises>>

    @GET("exercises/{exerciseId}")
    suspend fun getExerciseById(@Path("exerciseId") exerciseId: Int) : Response<NetworkContentInExercises>

//    TODO: hace falta?
    @GET("executions/{routineId}")
    suspend fun getExecutionById(@Path("routineId") routineId: Int, @Query("page") page: Int,
                                 @Query("size") size: Int, @Query("orderBy") orderBy: String,
                                 @Query("direction") direction: String) :
            Response<NetworkPagedContent<NetworkContentExecutions>>

    @GET("favourites")
    suspend fun getFavourites(@Query("page") page: Int, @Query("size") size: Int) :
            Response<NetworkPagedContent<NetworkRoutines>>

    @POST("favourites/{routineId}")
    suspend fun addFavouriteRoutine(@Path("routineId") routineId: Int) : Response<Unit>

    @DELETE("favourites/{routineId}")
    suspend fun deleteFavouriteRoutine(@Path("routineId") routineId: Int) : Response<Unit>
}
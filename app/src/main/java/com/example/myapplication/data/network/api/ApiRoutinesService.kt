package com.example.myapplication.data.network.api

import com.example.myapplication.data.network.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiRoutinesService {
    //    TODO: chequear si necesitamos todas las implementaciones!!!!

    @GET("routines")
    suspend fun getRoutines(
        @Query("categoryId") categoryId: Int? = null,
        @Query("userId") userId: Int? = null,
//        TODO pasar a un enum con los valores rookie, beginner, intermediate, advanced, expert
        @Query("difficulty") difficulty: String? = null,
        @Query("score") score: Int? = null,
        @Query("search") search: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        // TODO pasar a un enum id, name, detail, date, score, difficulty, category, user
        @Query("orderBy") orderBy: String? = null,
        // TODO pasar a un enum de asc o desc
        @Query("direction") sort: String? = null,
    ): Response<NetworkPagedContent<NetworkRoutines>>

    @GET("routines/{routineId}")
    suspend fun getRoutine(
        @Path("routineId") routineId: Int
    ): Response<NetworkRoutines>

    @GET("routines/{routineId}/cycles")
    suspend fun getRoutineCycles(
        @Path("routineId") routineId: Int,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("orderBy") orderBy: String? = null,
        @Query("direction") direction: String? = null,
    ): Response<NetworkPagedContent<NetworkRoutineCycle>>

    @GET("routines/{routineId}/cycles/{cycleId}")
    suspend fun getRoutineCyclesById(
        @Path("routineId") routineId: Int, @Path("cycleId") cycleId: Int
    ): Response<NetworkRoutineCycle>

    @GET("cycles/{cycleId}/exercises")
    suspend fun getCycleExercises(
        @Path("cycleId") cycleId: Int,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        // TODO cambiar por un enum
        @Query("orderBy") orderBy: String? = null,
        // TODO cambiar por un enum
        @Query("direction") direction: String? = null,
    ): Response<NetworkPagedContent<NetworkContentExercise>>

    @GET("cycles/{cycleId}/exercises/{exerciseId}")
    suspend fun getCycleExercisesById(
        @Path("cycleId") cycleId: Int, @Path("exerciseId") exerciseId: Int
    ): Response<NetworkContentExercise>

    @GET("exercises")
    suspend fun getExercises(
        @Query("search") search: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("orderBy") orderBy: String? = null,
        @Query("direction") direction: String? = null,
    ): Response<NetworkPagedContent<NetworkContentInExercises>>

    @GET("exercises/{exerciseId}")
    suspend fun getExerciseById(
        @Path("exerciseId") exerciseId: Int
    ): Response<NetworkContentInExercises>

    //    TODO: hace falta?
    @GET("executions/{routineId}")
    suspend fun getExecutionById(
        @Path("routineId") routineId: Int,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("orderBy") orderBy: String? = null,
        @Query("direction") direction: String? = null,
    ): Response<NetworkPagedContent<NetworkContentExecutions>>

    @GET("favourites")
    suspend fun getFavourites(
        @Query("page") page: Int? = null, @Query("size") size: Int? = null
    ): Response<NetworkPagedContent<NetworkRoutines>>

    @POST("favourites/{routineId}")
    suspend fun addFavouriteRoutine(@Path("routineId") routineId: Int): Response<Unit>

    @DELETE("favourites/{routineId}")
    suspend fun deleteFavouriteRoutine(@Path("routineId") routineId: Int): Response<Unit>
}
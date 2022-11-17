package com.example.myapplication.data.network

import com.example.myapplication.data.network.api.ApiRoutinesService
import com.example.myapplication.data.network.model.NetworkContentExercise
import com.example.myapplication.data.network.model.NetworkPagedContent
import com.example.myapplication.data.network.model.NetworkRoutineCycle
import com.example.myapplication.data.network.model.NetworkRoutines

class RoutineRemoteDataSource(
    private val apiRoutinesService: ApiRoutinesService
) : RemoteDataSource() {

    suspend fun getRoutines(
        categoryId: Int? = null,
        userId: Int? = null,
        difficulty: String? = null,
        score: Int? = null,
        search: String? = null,
        page: Int? = null,
        size: Int? = null,
        orderBy: String? = null,
        sort: String? = null,
    ): NetworkPagedContent<NetworkRoutines> {
        val response = handleApiResponse {
            apiRoutinesService.getRoutines(
                categoryId,
                userId,
                difficulty,
                score,
                search,
                page,
                size,
                orderBy,
                sort
            )
        }
        return response
    }

    suspend fun getRoutine(routineId: Int): NetworkRoutines {
        return handleApiResponse { apiRoutinesService.getRoutine(routineId) }
    }

    suspend fun getRoutineCycles(routineId: Int): NetworkPagedContent<NetworkRoutineCycle> {
        return handleApiResponse {
            apiRoutinesService.getRoutineCycles(
                routineId = routineId,
                orderBy = "order",
                direction = "asc"
            )
        }
    }

    suspend fun getCycleExercises(cycleId: Int): NetworkPagedContent<NetworkContentExercise> {
        return handleApiResponse {
            apiRoutinesService.getCycleExercises(
                cycleId = cycleId,
                orderBy = "order",
                direction = "asc"
            )
        }
    }

    suspend fun getFavourites(page: Int? = null, size: Int? = null): NetworkPagedContent<NetworkRoutines> {
        return handleApiResponse {
            apiRoutinesService.getFavourites(page = page, size = size)
        }
    }

    suspend fun markFavourite(routineId: Int) {
        handleApiResponse {
            apiRoutinesService.addFavouriteRoutine(routineId)
        }
    }

    suspend fun unmarkFavourite(routineId: Int) {
        handleApiResponse {
            apiRoutinesService.deleteFavouriteRoutine(routineId)
        }
    }

}
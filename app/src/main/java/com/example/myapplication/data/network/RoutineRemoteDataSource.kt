package com.example.myapplication.data.network

import com.example.myapplication.data.model.CycleExercise
import com.example.myapplication.data.model.Routine
import com.example.myapplication.data.model.RoutineCycle
import com.example.myapplication.data.network.api.ApiRoutinesService
import com.example.myapplication.data.network.model.NetworkPagedContent
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
    ): List<Routine> {
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
        return response.content.map { it.asModel() }
    }

    suspend fun getRoutine(routineId: Int): Routine {
        return handleApiResponse { apiRoutinesService.getRoutine(routineId) }.asModel()
    }

    suspend fun getRoutineCycles(routineId: Int): List<RoutineCycle> {
        return handleApiResponse {
            apiRoutinesService.getRoutineCycles(
                routineId = routineId,
                orderBy = "order",
                direction = "asc"
            )
        }.content.map {
            it.asModel()
        }
    }

    suspend fun getCycleExercises(cycleId: Int): List<CycleExercise> {
        return handleApiResponse {
            apiRoutinesService.getCycleExercises(
                cycleId = cycleId,
                orderBy = "order",
                direction = "asc"
            )
        }.content.map {
            it.asModel()
        }
    }

    suspend fun getFavourites(): NetworkPagedContent<NetworkRoutines> {
        return handleApiResponse {
            apiRoutinesService.getFavourites()
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
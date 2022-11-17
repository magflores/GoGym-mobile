package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Routine
import com.example.myapplication.data.network.RoutineRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository(
    private val remoteDataSource: RoutineRemoteDataSource
) {
    private val favsMutex = Mutex()
    private var favs: List<Routine> = emptyList()
    private val currentMutex = Mutex()
    private var currentRoutine: Routine? = null

    suspend fun getFavs(refresh: Boolean = false): List<Routine> {
        if (refresh || favs.isEmpty()) {
            val result = remoteDataSource.getFavourites()

            favsMutex.withLock {
                this.favs = result.content.map { it.asModel() }
            }
        }

        return favsMutex.withLock { this.favs }
    }

    suspend fun getRoutine(routineId: Int, refresh: Boolean = false): Routine {
        if (refresh || currentRoutine == null || currentRoutine?.id != routineId) {
            val routine = remoteDataSource.getRoutine(routineId).asModel()
            val cycles = remoteDataSource.getRoutineCycles(routineId).content.map { it.asModel() }
            val exercises = cycles.map { cycle ->
                remoteDataSource.getCycleExercises(cycle.id).content.map { it.asModel() }
            }
            val fullCycles = cycles.zip(exercises) { cycle, cycleexercises ->
                cycle.copy(exercises = cycleexercises)
            }
            val fullRoutine = Routine(
                id = routine.id,
                name = routine.name,
                detail = routine.detail,
                difficulty = routine.difficulty,
                score = routine.score,
                cycles = fullCycles,
                date = routine.date,
                isPublic = routine.isPublic,
                user = routine.user,
                category = routine.category,
                metadata = routine.metadata
            )
            currentMutex.withLock {
                this.currentRoutine = fullRoutine
            }
        }

        return currentMutex.withLock { this.currentRoutine!! }
    }
}
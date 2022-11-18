package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Routine
import com.example.myapplication.data.network.RoutineRemoteDataSource
import com.example.myapplication.data.network.model.NetworkReviewInformation
import com.example.myapplication.data.network.model.NetworkRoutines
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository(
    private val remoteDataSource: RoutineRemoteDataSource
) {
    private val favsMutex = Mutex()
    private var favs: List<Routine> = emptyList()
    private val currentMutex = Mutex()
    private var currentRoutine: Routine? = null
    private val routinesMutex = Mutex()
    private var routines: List<Routine> = emptyList()
    private var prevOrder: String? = null
    private var prevSort: String? = null

    suspend fun getFavs(refresh: Boolean = false): List<Routine> {
        if (refresh || favs.isEmpty()) {
            val result = remoteDataSource.getFavourites(size = PAGE_SIZE)

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

    suspend fun getAllRoutines(refresh: Boolean = false, orderBy: String, sort: String): List<Routine> {
        if (refresh || routines.isEmpty() || orderBy != prevOrder || sort != prevSort) {
            val result = remoteDataSource.getRoutines(
                orderBy = orderBy,
                sort = sort,
                size = PAGE_SIZE
            )

            routinesMutex.withLock {
                this.routines = result.content.map(NetworkRoutines::asModel)
            }
        }

        return routinesMutex.withLock { this.routines }
    }

    suspend fun searchRoutines(search: String, orderBy: String, sort: String): List<Routine> {
        val result = remoteDataSource.getRoutines(
            search = search,
            orderBy = orderBy,
            sort = sort,
            size = PAGE_SIZE
        )

        routinesMutex.withLock {
            this.routines = result.content.map(NetworkRoutines::asModel)
        }

        return routinesMutex.withLock { this.routines }
    }

    suspend fun isFavorite(routineId: Int): Boolean {
        if (this.routines.isEmpty()) {
            getFavs(true)
        }
        this.favs.forEach { elem ->
            if (elem.id == routineId) return true
        }
        return false
    }

    suspend fun addToFavourite(newRoutineId: Int){
        remoteDataSource.markFavourite(newRoutineId)
    }

    suspend fun deleteToFavourite(newRoutineId: Int){
        remoteDataSource.unmarkFavourite(newRoutineId)
    }

    suspend fun setScore(routineId: Int, score: Int, review: String) {
        remoteDataSource.setScore(routineId,
            NetworkReviewInformation(score = score, review = review))
    }

    suspend fun getUserRoutines(userId: Int, orderBy: String, sort: String): List<Routine> {
        val result = remoteDataSource.getRoutines(
            userId = userId,
            orderBy = orderBy,
            sort = sort,
            size = PAGE_SIZE
        )

        routinesMutex.withLock {
            this.routines = result.content.map(NetworkRoutines::asModel)
        }

        return routinesMutex.withLock { this.routines }
    }

    companion object {
        const val PAGE_SIZE = 100000
    }
}
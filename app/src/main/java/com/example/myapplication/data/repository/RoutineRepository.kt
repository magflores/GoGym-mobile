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

    suspend fun getFavs(refresh: Boolean = false): List<Routine> {
        if (refresh || favs.isEmpty()) {
            val result = remoteDataSource.getFavourites()

            favsMutex.withLock {
                this.favs = result.content.map { it.asModel() }
            }
        }

        return favsMutex.withLock { this.favs }
    }
}
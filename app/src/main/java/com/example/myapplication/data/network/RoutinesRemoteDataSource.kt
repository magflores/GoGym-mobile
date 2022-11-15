package com.example.myapplication.data.network

import com.example.myapplication.data.model.NetworkFavourites
import com.example.myapplication.data.network.api.ApiRoutinesService
import com.example.myapplication.util.SessionManager
import retrofit2.Response
import retrofit2.http.Query

class RoutinesRemoteDataSource(
    private val sessionManager: SessionManager,
    private val apiRoutinesService: ApiRoutinesService
) : RemoteDataSource() {

    suspend fun favourites(page: Int, size: Int) {
        val response = handleApiResponse {
            apiRoutinesService.favourites(page, size)
        }
//        return response
    }
}
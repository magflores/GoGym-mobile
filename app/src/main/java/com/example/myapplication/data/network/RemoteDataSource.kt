package com.example.myapplication.data.network

import com.example.myapplication.data.network.model.NetworkError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.io.IOException

abstract class RemoteDataSource {

    suspend fun <T : Any> handleApiResponse(
        execute: suspend () -> Response<T>
    ) : T {
        try {
            val respone = execute()
            val body = respone.body()
            if (respone.isSuccessful && body != null)
                return body
            respone.errorBody()?.let {
                val gson = Gson()
                val error = gson.fromJson<NetworkError>(it.string(), object : TypeToken<NetworkError?>() {}.type)
                throw DataSourceException(error.code, error.description, error.details)
            }
            throw DataSourceException(UNEXPECTED_ERROR_CODE, "Missing error", null)
        } catch (e: IOException) {
            throw DataSourceException(CONNECTION_ERROR_CODE, "Connection error", getDetailsFromException(e))
        } catch (e: Exception) {
            throw DataSourceException(UNEXPECTED_ERROR_CODE, "Unexpected error", getDetailsFromException(e))
        }
    }

    private fun getDetailsFromException(e: Exception): List<String> {
        return if (e.message != null) listOf(e.message!!) else emptyList()
    }

    companion object {
        const val CONNECTION_ERROR_CODE = 98
        const val UNEXPECTED_ERROR_CODE = 99
    }
}
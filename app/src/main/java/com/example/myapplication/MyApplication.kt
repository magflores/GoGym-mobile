package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.model.Routine
import com.example.myapplication.data.network.RoutineRemoteDataSource
import com.example.myapplication.data.network.UserRemoteDataSource
import com.example.myapplication.data.network.api.RetrofitClient
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.util.SessionManager

class MyApplication : Application() {

    private val userRemoteDataSource : UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getApiUserService(this))

    val sessionManager : SessionManager
        get() = SessionManager(this)

    val userRepository : UserRepository
        get() = UserRepository(userRemoteDataSource)

    val routineRemoteDataSource: RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.getApiRoutinesService(this))
}
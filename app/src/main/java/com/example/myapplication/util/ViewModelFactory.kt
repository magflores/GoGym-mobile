package com.example.myapplication.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.myapplication.data.repository.RoutineRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.ui.ExampleViewModel
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel
import com.example.myapplication.ui.screens.favroutines.FavRoutinesViewModel
import com.example.myapplication.ui.screens.play.PlayViewModel

class ViewModelFactory constructor(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val routineRepository: RoutineRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(ExampleViewModel::class.java) ->
                ExampleViewModel(sessionManager, userRepository)
            isAssignableFrom(FavRoutinesViewModel::class.java) ->
                FavRoutinesViewModel(routineRepository)
            isAssignableFrom(PlayViewModel::class.java) ->
                PlayViewModel(routineRepository)
            isAssignableFrom(RoutinesViewModel::class.java) ->
                RoutinesViewModel(routineRepository)
            else ->
                throw java.lang.IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
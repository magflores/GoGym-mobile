package com.example.myapplication.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.util.SessionManager
import kotlinx.coroutines.launch

class ExampleViewModel(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository
) : ViewModel() {

    var uiState by mutableStateOf(ExampleUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set


    fun toggleView() {
        uiState = uiState.copy(
            typeOfView_List_Grid = !uiState.typeOfView_List_Grid
        )
    }

    fun login(username: String, password: String, onLogIn: (() -> Unit)? = null) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null,
        )
        kotlin.runCatching {
            userRepository.login(username, password)
        }.onSuccess {
            uiState = uiState.copy(
                isFetching = false,
                isAuthenticated = true,
            )
            if (onLogIn != null) {
                onLogIn()
            }
        }.onFailure {
            if (it.message != BuildConfig.API_UNAUTHORIZED_MESSAGE)
                uiState = uiState.copy(
                    isFetching = false,
                    message = it.message
                )
        }
    }

    fun getCurrentUser() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        kotlin.runCatching {
            userRepository.getCurrentUser(false)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentUser = response
            )
        }.onFailure { e ->
            if (e.message != BuildConfig.API_UNAUTHORIZED_MESSAGE)
                uiState = uiState.copy(
                    isFetching = false,
                    message = e.message
                )
        }
    }

    fun dismissMessage() {
        uiState = uiState.copy(message = null)
    }

    fun logout() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        kotlin.runCatching {
            userRepository.logout()
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentUser = null
            )
        }.onFailure { e ->
            if (e.message != BuildConfig.API_UNAUTHORIZED_MESSAGE)
                uiState = uiState.copy(
                    isFetching = false,
                    message = e.message
                )
        }
    }

}
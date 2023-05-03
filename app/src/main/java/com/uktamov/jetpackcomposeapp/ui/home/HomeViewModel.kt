package com.uktamov.jetpackcomposeapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uktamov.jetpackcomposeapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val TAG = "HomeViewModel"
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> get() = _state

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            repository.getUsers()
                .onStart {
                    _state.value = _state.value.copy(isLoading = true)
                }
                .catch {
                    _state.value = _state.value.copy(isLoading = false, message = it.message.toString())
                    Log.d(TAG, "loadProducts: ${it.message}")
                }
                .collect {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        success = it.body()?.data ?: emptyList()
                    )
                }
        }
    }
}
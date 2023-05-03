package com.uktamov.jetpackcomposeapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uktamov.jetpackcomposeapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: UserRepository
) :ViewModel(){
    private var _state:MutableStateFlow<DetailState> = MutableStateFlow(DetailState())
    val state:StateFlow<DetailState> get() = _state

    fun onEvent(event: DetailEvent) {
        if (event is DetailEvent.OnGetUser) {
            viewModelScope.launch {
                repository.getUserById(event.id)
                    .onStart {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                    .catch {
                        _state.value =
                            _state.value.copy(isLoading = false, message = it.message.toString())
                    }
                    .collect {
                        _state.value = _state.value.copy(success = it.body(), isLoading = false)
                    }
            }
        }
    }
}
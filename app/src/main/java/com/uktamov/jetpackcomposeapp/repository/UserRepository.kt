package com.uktamov.jetpackcomposeapp.repository

import com.uktamov.jetpackcomposeapp.network.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUsers() = flow { emit(apiService.getUsers()) }

    suspend fun getUserById(id: Int) = flow { emit(apiService.getUserById(id)) }
}
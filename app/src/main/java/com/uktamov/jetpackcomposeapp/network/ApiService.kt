package com.uktamov.jetpackcomposeapp.network

import com.uktamov.jetpackcomposeapp.model.Data
import com.uktamov.jetpackcomposeapp.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/users")
    suspend fun getUsers():Response<UserResponse>

    @GET("/api/users/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): Response<Data>
}
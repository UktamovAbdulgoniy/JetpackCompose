package com.uktamov.jetpackcomposeapp.ui.home

import com.uktamov.jetpackcomposeapp.model.Data


data class HomeState(
    val isLoading: Boolean = false,
    val message: String = "",
    val success: List<Data> = emptyList()
)
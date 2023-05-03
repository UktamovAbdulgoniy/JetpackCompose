package com.uktamov.jetpackcomposeapp.ui.detail

import com.uktamov.jetpackcomposeapp.model.Data


data class DetailState(
    val isLoading: Boolean = false,
    val message: String = "",
    val success: Data? = null
)
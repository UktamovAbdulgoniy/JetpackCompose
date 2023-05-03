package com.uktamov.jetpackcomposeapp.ui.detail

sealed interface DetailEvent {
    data class OnGetUser(val id:Int): DetailEvent
}
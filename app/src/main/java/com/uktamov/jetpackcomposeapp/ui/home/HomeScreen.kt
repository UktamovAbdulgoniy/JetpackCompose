package com.uktamov.jetpackcomposeapp.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.uktamov.jetpackcomposeapp.ui.component.Loading
import com.uktamov.jetpackcomposeapp.ui.component.UserItem

@Composable
fun HomeScreen(
    uiState: HomeState,
    onClick: (Int) -> Unit
) {
    if (uiState.isLoading) {
        Loading()
    }
    println("@@@$uiState")
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(
            uiState.success,
            key = { it.id }
        ) { user ->
            UserItem(
                user = user,
                onClick = {
                    onClick(user.id)
                }
            )
        }
    }
}

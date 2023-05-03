package com.uktamov.jetpackcomposeapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.uktamov.jetpackcomposeapp.ui.component.Loading

@Composable
fun DetailScreen(
    uiState: DetailState,
    onEvent: (DetailEvent) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        onEvent(DetailEvent.OnGetUser(0))
    }
    if (uiState.isLoading) {
        Loading()
    }
    uiState.success?.let { user ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val painter = rememberCoilPainter(request = user.avatar)
            Image(
                painter = painter,
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(5.dp))
            Column(modifier = Modifier.padding(15.dp)) {
                Text(
                    text = user.first_name,
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = user.last_name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}
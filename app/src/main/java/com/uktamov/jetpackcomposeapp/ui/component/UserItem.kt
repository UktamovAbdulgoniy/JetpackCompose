package com.uktamov.jetpackcomposeapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.uktamov.jetpackcomposeapp.model.Data

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserItem(
    user: Data,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(350.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        val painter = rememberCoilPainter(request = user.avatar)
        Column(
            modifier = Modifier
                .padding(10.dp)
        ){

            Text(
                text = user.first_name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Clip
            )

            Spacer(modifier = Modifier.height(5.dp))

            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp),
                contentScale = ContentScale.Crop
            )

        }
    }
}
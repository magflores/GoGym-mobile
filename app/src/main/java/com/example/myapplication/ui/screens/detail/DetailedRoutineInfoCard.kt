package com.example.myapplication.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.model.Routine

@Composable
fun RoutineInfoCard(modifier: Modifier = Modifier, routine: Routine) {
    Surface(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = routine.name, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                Text(text = routine.detail, fontWeight = FontWeight.Light, fontSize = 20.sp)
                Text(text = "FAVORITO: ${routine.isFavorite}")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo2docuatri),
                    contentDescription = "Author profile picture",
                    modifier = Modifier.size(80.dp)
                )
                Column {
                    Text(text = routine.user.username)
                    Text(text = routine.user.lastActivity.toString())
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = routine.date.toString())
                Text(text = routine.score.toString())
                Text(text = routine.difficulty)
                Text(text = routine.category)
            }
        }
    }
}

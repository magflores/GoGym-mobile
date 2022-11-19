package com.example.myapplication.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel
import com.example.myapplication.ui.theme.Green200
import com.example.myapplication.ui.theme.Shapes
import org.ocpsoft.prettytime.PrettyTime

@Composable
fun RoutineInfoCard(modifier: Modifier = Modifier, routinesViewModel: RoutinesViewModel) {
    val routine = routinesViewModel.uiState.currentRoutine
    routine?.let {
        Surface(
            modifier = modifier.fillMaxWidth(),
            color = Green200,
            shape = Shapes.medium
        ) {
            val prettyTime = PrettyTime()
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = routine.name, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                    Text(text = routine.detail, fontWeight = FontWeight.Light, fontSize = 20.sp)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row() {
                        Text(text = stringResource(R.string.score) + ": ")
                        Text(text = routine.score.toString())
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(R.string.difficulty) + ": ")
                    Text(text = routine.difficulty)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(R.string.category) + ": ")
                    Text(text = if (routine.category != "") routine.category else "-")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (routine.user.avatarUrl != "") {
                        AsyncImage(
                            model = routine.user.avatarUrl,
                            contentDescription = stringResource(R.string.authorPicture),
                            modifier = Modifier.size(80.dp)
                        )
                    } else {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = stringResource(R.string.authorPicture),
                            modifier = Modifier.size(80.dp)
                        )
                    }

                    Column {
                        Text(text = routine.user.username)
                        Text(text = prettyTime.format(routine.user.lastActivity))
                    }
                }
            }
        }
    }
}

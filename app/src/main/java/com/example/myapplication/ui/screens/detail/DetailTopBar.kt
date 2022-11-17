package com.example.myapplication.ui.screens.detail

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel

@Composable
fun RoutineTopBar(onBack: () -> Unit, routineId: Int, routinesViewModel: RoutinesViewModel) {
    TopAppBar(title = {
        Text("Routine")
    }, navigationIcon = {
        IconButton(onClick = onBack) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
    }, actions = {
        IconButton(onClick = { routinesViewModel.markFavorite(routineId) }) {
            if (routinesViewModel.isFavorite(routineId))
                Icon(Icons.Filled.Favorite, contentDescription = stringResource(R.string.favourite))
            else
                Icon(Icons.Outlined.Favorite, contentDescription = stringResource(R.string.favourite))
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Share, contentDescription = stringResource(R.string.share))
        }
    })
}

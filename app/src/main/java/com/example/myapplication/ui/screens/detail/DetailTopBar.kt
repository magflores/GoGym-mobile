package com.example.myapplication.ui.screens.detail

import android.content.Intent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel

@Composable
fun RoutineTopBar(
    onBack: () -> Unit,
    routinesViewModel: RoutinesViewModel,
    onRatingClick: () -> Unit,
    title: String
) {
    val routine = routinesViewModel.uiState.currentRoutine
    val routineId = routine?.id
    TopAppBar(title = {
        Text(title)
    }, navigationIcon = {
        IconButton(onClick = onBack) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
    }, actions = {
        IconButton(onClick = { onRatingClick() }) {
            Icon(Icons.Filled.Star,
                contentDescription = stringResource(id = R.string.score))
        }
        IconButton(onClick = {
            if (routineId != null) {
                routinesViewModel.markFavourite(routineId)
            }
        }) {
            routine?.let {
                if (it.isFavorite)
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = stringResource(R.string.favourite)
                    )
                else
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = stringResource(R.string.favourite)
                    )
            }

        }
        if (routineId != null) {
            ShareRoutineButton(routineId = routineId) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = stringResource(R.string.share)
                )
            }
        }
    })
}

@Composable
fun ShareRoutineButton(routineId: Int, icon: @Composable () -> Unit) {
    val context = LocalContext.current
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "${BuildConfig.WEBPAGE_BASE_URL}/viewroutine/$routineId")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    IconButton(onClick = { context.startActivity(shareIntent) }, content = icon)
}














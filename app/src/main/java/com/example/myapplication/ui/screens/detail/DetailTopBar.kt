package com.example.myapplication.ui.screens.detail

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel

@Composable
fun RoutineTopBar(onBack: () -> Unit, routineId: Int,
                  routinesViewModel: RoutinesViewModel, title: String) {
    TopAppBar(title = {
        Text(title)
    }, navigationIcon = {
        IconButton(onClick = onBack) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
    }, actions = {
        IconButton(onClick = { routinesViewModel.markFavourite(routineId) }) {
            if (routinesViewModel.isFavorite(routineId))
                Icon(Icons.Filled.Favorite, contentDescription = stringResource(R.string.favourite))
            else
                Icon(Icons.Outlined.FavoriteBorder, contentDescription = stringResource(R.string.favourite))
        }
        IconButton(onClick = { /*shareRoutine(routineId)*/ }) {
            Icon(imageVector = Icons.Default.Share, contentDescription = stringResource(R.string.share))
        }
    })
}
//
//@Composable
//fun shareRoutine(routineId: Int) {
//    val context = LocalContext.current
//    val deepLinkIntent = Intent(
//        Intent.ACTION_VIEW,
//        "https://gogym.com/routine/$routineId".toUri(),
//        context,
//        MyApplication::class.java
//    )
//}

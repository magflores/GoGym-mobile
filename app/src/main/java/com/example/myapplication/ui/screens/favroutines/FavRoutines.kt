package com.example.myapplication.ui.screens.favroutines

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.BottomNavLayout
import com.example.myapplication.FavouritesRoutinesAppBar
import com.example.myapplication.R
import com.example.myapplication.ui.ExampleViewModel
import com.example.myapplication.ui.screens.allroutines.ListRow
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel

//import com.google.accompanist.swiperefresh.SwipeRefresh
//import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavRoutines(
    navController: NavController,
    onNotLoggedIn: () -> Unit,
    viewModel: FavRoutinesViewModel,
    mainViewModel: ExampleViewModel,
    routinesViewModel: RoutinesViewModel,
    onGoToRoutine: (Int) -> Unit
) {
    LaunchedEffect(mainViewModel.uiState.isAuthenticated) {
        if (!mainViewModel.uiState.isAuthenticated) onNotLoggedIn()
    }
    LaunchedEffect(Unit) {
        viewModel.getFavourites()
    }
    val uiState = viewModel.uiState
    val scaffoldState = rememberScaffoldState()
    BottomNavLayout(navController = navController,
        mainViewModel = mainViewModel) { padding ->
        Scaffold(
            modifier = Modifier.padding(padding),
            topBar = {
                FavouritesRoutinesAppBar(
                    title = stringResource(
                        id = R.string.fav_routines),
                    viewModel = viewModel,
                    mainViewModel = mainViewModel,
                )
            },
            scaffoldState = scaffoldState
        ) { scaffoldPadding ->
//            SwipeRefresh

//            TODO: esto va:
//            SwipeRefresh(
//                state = rememberSwipeRefreshState(isRefreshing = uiState.isFetching),
//                onRefresh = { viewModel.getFavourites(true) },
//            ) {
                Column(
                    modifier = Modifier.padding(scaffoldPadding)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val configuration = LocalConfiguration.current
                        if (uiState.stateTypeOfView_List_Grid) {
                            LazyColumn(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement
                                    .spacedBy(4.dp),
                                contentPadding = PaddingValues(
                                    horizontal = 16.dp, vertical = 8.dp),
                                modifier = Modifier
                                    .background(Color.White)
                            ) {
                                uiState.favourites?.forEach {
                                    this.item {
                                        ListRow(
                                            model = it,
                                            onGoToRoutine = onGoToRoutine)
                                    }
                                }
                            }
                        } else {
                            when (configuration.orientation) {
                                Configuration.ORIENTATION_LANDSCAPE -> {
                                    LazyVerticalGrid(
                                        columns = GridCells.Adaptive(200.dp),
                                        verticalArrangement = Arrangement
                                            .spacedBy(4.dp),
                                        contentPadding =
                                        PaddingValues(
                                            horizontal = 16.dp,
                                            vertical = 8.dp),
                                        modifier = Modifier
                                            .background(Color.White)
                                    ) {
                                        uiState.favourites?.forEach {
                                            this.item {
                                                ListRow(
                                                    model = it,
                                                    onGoToRoutine = onGoToRoutine)
                                            }
                                        }
                                    }
                                }
                                Configuration.ORIENTATION_PORTRAIT -> {
                                    LazyVerticalGrid(
                                        columns = GridCells.Adaptive(150.dp),
                                        verticalArrangement = Arrangement
                                            .spacedBy(4.dp),
                                        contentPadding =
                                        PaddingValues(
                                            horizontal = 16.dp,
                                            vertical = 8.dp),
                                        modifier = Modifier
                                            .padding(padding)
                                            .background(Color.White)
                                    ) {
                                        uiState.favourites?.forEach {
                                            this.item {
                                                ListRow(
                                                    model = it,
                                                    onGoToRoutine = onGoToRoutine)
                                            }
                                        }
                                    }
                                }
                                Configuration.ORIENTATION_UNDEFINED -> {
                                    TODO()
                                }
                                Configuration.ORIENTATION_SQUARE -> {
                                    TODO()
                                }
                            }
                        }
                    }
                }
//            }
        }
    }

    if (viewModel.uiState.hasError) {
        val actionLabel = stringResource(id = R.string.dismiss)

        LaunchedEffect(scaffoldState.snackbarHostState) {
            val result = scaffoldState.snackbarHostState.showSnackbar(
                message = uiState.message!!,
                actionLabel = actionLabel
            )
            when (result) {
                SnackbarResult.Dismissed -> viewModel.dismissMessage()
                SnackbarResult.ActionPerformed -> viewModel.dismissMessage()
            }
        }
    }
}

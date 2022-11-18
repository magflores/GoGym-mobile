package com.example.myapplication.ui.screens.favroutines

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.*
import com.example.myapplication.R
import com.example.myapplication.ui.ExampleViewModel
import com.example.myapplication.ui.screens.allroutines.ListRow
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel
import com.example.myapplication.ui.screens.allroutines.stateTypeOfView_List_Grid
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavRoutines(
    navController: NavController,
    onNotLoggedIn: () -> Unit,
    viewModel: FavRoutinesViewModel,
    mainViewModel: ExampleViewModel,
    routinesViewModel: RoutinesViewModel,
    onGoToRoutine: (Int) -> Unit,
    navigateOnLogout: () -> Unit
) {
    LaunchedEffect(mainViewModel.uiState.isAuthenticated) {
        if (!mainViewModel.uiState.isAuthenticated) onNotLoggedIn()
    }
    LaunchedEffect(Unit) {
        viewModel.getFavourites()
    }
    val uiState = viewModel.uiState
    val routinesUiState = routinesViewModel.uiState
    val scaffoldState = rememberScaffoldState()

    var showPopup by remember{ mutableStateOf(false) }
//    val onPopupDismissed = {showPopup = false}
    val onSortClick = {showPopup = true}

    BottomNavLayout(
        navController = navController,
        mainViewModel = mainViewModel
    ) { padding ->
        Scaffold(
            modifier = Modifier.padding(padding),
            topBar = {
                AllRoutinesAppBar(
                    title = stringResource(R.string.fav_routines),
                    viewModel = routinesViewModel,
                    mainViewModel = mainViewModel,
                    onSortClick = onSortClick,
                    showSortButton = false,
                    navigateOnLogout = navigateOnLogout
                )
            },
            scaffoldState = scaffoldState
        ) { scaffoldPadding ->
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = uiState.isFetching),
                onRefresh = { viewModel.getFavourites(true) },
            ) {
                Column(
                    modifier = Modifier
                        .padding(scaffoldPadding)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val configuration = LocalConfiguration.current
                        if (routinesUiState.stateTypeOfView_List_Grid) {
                            LazyColumn(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement
                                    .spacedBy(4.dp),
                                contentPadding = PaddingValues(
                                    horizontal = 16.dp, vertical = 8.dp
                                ),
                                modifier = Modifier
                                    .background(Color.White)
                            ) {
                                uiState.favourites?.forEach {
                                    this.item {
                                        ListRow(
                                            model = it,
                                            onGoToRoutine = onGoToRoutine
                                        )
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
                                            vertical = 8.dp
                                        ),
                                        modifier = Modifier
                                            .background(Color.White)
                                    ) {
                                        uiState.favourites?.forEach {
                                            this.item {
                                                ListRow(
                                                    model = it,
                                                    onGoToRoutine = onGoToRoutine
                                                )
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
                                            vertical = 8.dp
                                        ),
                                        modifier = Modifier
                                            .padding(padding)
                                            .background(Color.White)
                                    ) {
                                        uiState.favourites?.forEach {
                                            this.item {
                                                ListRow(
                                                    model = it,
                                                    onGoToRoutine = onGoToRoutine
                                                )
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
            }
        }
    }

//    if (showPopup) {
//        SortPopup(onPopupDismissed)
//    }

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

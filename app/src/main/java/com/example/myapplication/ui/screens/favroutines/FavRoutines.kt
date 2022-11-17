package com.example.myapplication.ui.screens.favroutines

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.AllRoutinesAppBar
import com.example.myapplication.BottomNavLayout
import com.example.myapplication.R
import com.example.myapplication.ui.ExampleViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavRoutines(
    navController: NavController,
    onNotLoggedIn: () -> Unit,
    viewModel: FavRoutinesViewModel,
    mainViewModel: ExampleViewModel,
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
    BottomNavLayout(navController = navController, mainViewModel = mainViewModel) { padding ->
        Scaffold(
            modifier = Modifier.padding(padding),
            topBar = {
                AllRoutinesAppBar(
                    title = "Favourites",
                    typeView = true,
                    mainViewModel = mainViewModel
                )
            },
            scaffoldState = scaffoldState
        ) { scaffoldPadding ->
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = uiState.isFetching),
                onRefresh = { viewModel.getFavourites(true) },
            ) {
                Column(
                    modifier = Modifier.padding(scaffoldPadding)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(120.dp),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            uiState.favourites?.forEach() {
                                this.item {
                                    Card(modifier = Modifier.fillMaxWidth(),
                                        onClick = {
                                            it.id?.let { it1 -> onGoToRoutine(it1) }
                                        }) {
                                        Text(text = it.name)
                                    }
                                }
                            }
                        }
                    }
                }
            }
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

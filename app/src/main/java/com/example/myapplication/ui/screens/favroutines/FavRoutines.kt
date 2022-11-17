package com.example.myapplication.ui.screens.favroutines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.AllRoutinesAppBar
import com.example.myapplication.BottomNavLayout
import com.example.myapplication.R
import com.example.myapplication.R
import com.example.myapplication.Screen
import com.example.myapplication.ui.ExampleViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun FavRoutines(
    navController: NavController,
    onNotLoggedIn: () -> Unit,
    viewModel: FavRoutinesViewModel,
    mainViewModel: ExampleViewModel
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
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement
                                .spacedBy(4.dp),
                            contentPadding = PaddingValues(
                                horizontal = 16.dp, vertical = 8.dp),
                            modifier = Modifier
                                .background(Color.White)
                        ) {
                            uiState.favourites?.forEach() {
                                this.item{
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .wrapContentHeight()
                                    ) {
                                        Card(
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 12.dp, vertical = 5.dp),
                                            backgroundColor = MaterialTheme.colors.primary
                                        ) {
                                            Row {
                                                Column(
                                                    modifier = Modifier.fillMaxWidth(0.5F),
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                ) {
                                                    Text(
                                                        modifier = Modifier.padding(5.dp,0.dp),
                                                        text = it.name,
                                                        fontSize = 24.sp,
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = Color.Black
                                                    )
                                                    Text(
                                                        modifier = Modifier.padding(10.dp,10.dp),
                                                        text = stringResource(
                                                            id = R.string.created_by,
                                                            it.user.username),
                                                        fontSize = 20.sp,
                                                        color = Color.Black
                                                    )
                                                    Text(
                                                        modifier = Modifier.padding(10.dp,10.dp),
                                                        text = it.difficulty,
                                                        fontSize = 16.sp,
                                                        color = Color.Black
                                                    )
                                                }
                                                Column(
                                                    modifier = Modifier
                                                        .padding(5.dp, 0.dp)
                                                        .fillMaxWidth(),
                                                    horizontalAlignment = Alignment.End)
                                                {
                                                    Text(
                                                        modifier = Modifier.padding(5.dp,0.dp),
                                                        text = it.score.toString(),
                                                        fontSize = 24.sp,
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = Color.Black
                                                    )
                                                    Text(
                                                        modifier = Modifier.padding(10.dp,10.dp),
                                                        text = it.category,
                                                        fontSize = 20.sp,
                                                        color = Color.Black
                                                    )
                                                }
                                            }
                                        }
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
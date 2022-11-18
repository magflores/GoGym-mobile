package com.example.myapplication

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.myapplication.ui.ExampleViewModel
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel
import com.example.myapplication.ui.screens.allroutines.stateTypeOfView_List_Grid
import com.example.myapplication.ui.screens.favroutines.FavRoutinesViewModel

@Composable
fun AllRoutinesAppBar(
    title: String,
    viewModel: RoutinesViewModel,
    mainViewModel: ExampleViewModel,
    onSortClick: () -> Unit,
    showSortButton: Boolean = true
){
    var showMenu by remember {
        mutableStateOf(false)
    }
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = {
                viewModel.toggleView()
            }) {
                if (!viewModel.uiState.stateTypeOfView_List_Grid){
                    Icon(painterResource(id =
                    R.drawable.baseline_list_black_48),
                        contentDescription = null)
                }else {
                    Icon(painterResource(id =
                    R.drawable.baseline_grid_view_black_48),
                        contentDescription = null)
                }
            }
            IconButton(onClick = { showMenu = !showMenu  }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = stringResource(id = R.string.more))
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                if (showSortButton) {
                    DropdownMenuItem(onClick = onSortClick) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Icon(
                                imageVector = Icons.Default.Sort,
                                contentDescription = stringResource(id = R.string.sorttext)
                            )
                            Text(stringResource(R.string.sorttext))
                        }
                    }
                }
                DropdownMenuItem(onClick = { mainViewModel.logout() }) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
//                        TODO: imageVector = Icons.Default.Logout
                        Icon(imageVector = Icons.Default.Lock, contentDescription = stringResource(
                            id = R.string.logout
                        ))
                        Text(text = stringResource(id = R.string.logout))
                    }
                }
            }
        }
    )
}

@Composable
fun FavouritesRoutinesAppBar(
    title: String,
    viewModel: FavRoutinesViewModel,
    mainViewModel: ExampleViewModel
){
    var showMenu by remember {
        mutableStateOf(false)
    }
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = null)
            }
        },
        actions = {
//            IconButton(onClick = {
//                viewModel.toggleView()
//            }) {
//                if (!viewModel.uiState.stateTypeOfView_List_Grid){
//                    Icon(painterResource(id =
//                    R.drawable.baseline_list_black_48),
//                        contentDescription = null)
//                }else {
//                    Icon(painterResource(id =
//                    R.drawable.baseline_grid_view_black_48),
//                        contentDescription = null)
//                }
//            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Sort, contentDescription = stringResource(id = R.string.sorttext))
            }
            IconButton(onClick = { showMenu = !showMenu  }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = stringResource(id = R.string.more))
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(onClick = { mainViewModel.logout() }) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = stringResource(
                            id = R.string.logout
                        ))
                        Text(text = stringResource(id = R.string.logout))
                    }
                }
            }
        }
    )
}
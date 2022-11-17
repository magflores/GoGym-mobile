package com.example.myapplication

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.myapplication.ui.ExampleViewModel

@Composable
//fun AllRoutinesAppBar(title: String, content: @Composable (PaddingValues) -> Unit){
fun AllRoutinesAppBar(title: String, typeView: Boolean, mainViewModel: ExampleViewModel){
    /*
    Surface(color = Color.White) {
        Scaffold(
            content = content,
            topBar = {
                TopAppBar(
                    backgroundColor = Color.White,
                    title = {
                        Row {
                           Text(text = title)
                           Spacer(modifier = Modifier.width(26.dp))
                           Icon(Icons.Default.Add, contentDescription = null)
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                        }
                    }
                )
            }
        )
    }
    */
    var showMenu by remember{ mutableStateOf(false) }
    
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                if (typeView){ // true = lista
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
package com.example.myapplication

import android.icu.text.CaseMap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
//fun AllRoutinesAppBar(title: String, content: @Composable (PaddingValues) -> Unit){
fun AllRoutinesAppBar(title: String, typeView: Boolean){
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
        }
    )
}
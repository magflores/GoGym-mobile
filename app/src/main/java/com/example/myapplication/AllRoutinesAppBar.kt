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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.ExampleViewModel
import com.example.myapplication.ui.stateTypeOfView_List_Grid
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.lang.reflect.TypeVariable

@Composable
fun AllRoutinesAppBar(title: String, viewModel: ExampleViewModel){
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
        }
    )
}
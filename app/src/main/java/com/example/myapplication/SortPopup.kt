package com.example.myapplication

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.screens.allroutines.Order
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel
import com.example.myapplication.ui.screens.allroutines.Sort

@Composable
fun SortPopup(
    onPopupDismissed: () -> Unit,
    routinesViewModel: RoutinesViewModel,
    onRefresh: () -> Unit
    ) {


    val orderOptions = Order.values()
    val (orderSelected, onOrderSelected) = remember { mutableStateOf(routinesViewModel.uiState.orderBy) }
    val sortOptions = Sort.values()
    val (sortSelected, onSortSelected) = remember { mutableStateOf(routinesViewModel.uiState.sort) }
    val configuration = LocalConfiguration.current

    AlertDialog(onDismissRequest = onPopupDismissed, confirmButton = {
        Button(onClick = {
            routinesViewModel.orderBy(orderSelected)
            onPopupDismissed()
        }) {
            Text(text = stringResource(id = R.string.apply))
        }
    }, text = {
        when (configuration.orientation){
            Configuration.ORIENTATION_PORTRAIT -> {
                Row() {
                    Text(text = stringResource(R.string.order))
                    Column {
                        orderOptions.forEach { order ->
                            Row(
                                Modifier.selectable(
                                    selected = (order == orderSelected),
                                    onClick = { onOrderSelected(order) })
                            ) {
                                RadioButton(selected = (order == orderSelected),
                                    onClick = {
                                        onOrderSelected(order)
                                    })
                                Text(text = order.order)
                            }
                        }
                    }
                    Column() {
                        Text(text = stringResource(R.string.sorttext))
                        sortOptions.forEach { sort ->
                            Row(
                                Modifier.selectable(selected = (sort == sortSelected),
                                    onClick = { onSortSelected(sort) })
                            ) {
                                RadioButton(selected = (sort == sortSelected),
                                    onClick = { onSortSelected(sort) })
                                Text(text = sort.sort)
                            }
                        }
                    }
                }
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(text = stringResource(R.string.order))
                    Spacer(modifier = Modifier.padding(10.dp))
                    Row {
                        orderOptions.forEachIndexed {index, order ->
                            if (index < 4){
                                Column(
                                    Modifier.selectable(
                                        selected = (order == orderSelected),
                                        onClick = { onOrderSelected(order) })
                                ) {
                                    Text(
                                        modifier = Modifier.padding(horizontal = 6.dp),
                                        text = order.order)
                                    RadioButton(selected = (order == orderSelected),
                                        onClick = {
                                            onOrderSelected(order)
                                        })
                                }
                            }
                        }
                    }
                    Row {
                        orderOptions.forEachIndexed {index, order ->
                            if (index > 3) {
                                Column(
                                    Modifier.selectable(
                                        selected = (order == orderSelected),
                                        onClick = { onOrderSelected(order) })
                                ) {
                                    RadioButton(selected = (order == orderSelected),
                                        onClick = {
                                            onOrderSelected(order)
                                        }
                                    )
                                    Text(
                                        modifier = Modifier.padding(horizontal = 6.dp),
                                        text = order.order)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(14.dp))
                    Row {
                        Text(text = stringResource(R.string.sorttext))
                        sortOptions.forEach { sort ->
                            Column(
                                Modifier.selectable(
                                    selected = (sort == sortSelected),
                                    onClick = { onSortSelected(sort) }
                                )
                            ) {
                                RadioButton(selected = (sort == sortSelected),
                                    onClick = { onSortSelected(sort) })
                                Text(text = sort.sort)
                            }
                        }
                    }
                }
            }
        }
    })
}
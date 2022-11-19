package com.example.myapplication

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel

@Composable
fun ScorePopup(
    onPopupDismissed: () -> Unit,
    routinesViewModel: RoutinesViewModel,
    myRoutineId: Int
) {
    var score by remember { mutableStateOf(0) }
    val scoreOptions = arrayOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val configuration = LocalConfiguration.current

    AlertDialog(
        onDismissRequest = onPopupDismissed,
        confirmButton = {
            Button(onClick = {
                routinesViewModel.setScore(myRoutineId, score, "")
                routinesViewModel.getRoutine(myRoutineId, true)
                onPopupDismissed()
            }) {
                Text(text = stringResource(id = R.string.apply))
            }
        }, text = {
            when (configuration.orientation){
                Configuration.ORIENTATION_PORTRAIT -> {
                    Row {
                        Text(text = stringResource(id = R.string.score))
                        Column {
                            scoreOptions.forEach { elem ->
                                Row(
                                    Modifier.selectable(selected = (elem == score),
                                        onClick = { score = elem })
                                ) {
                                    RadioButton(selected = (score == elem),
                                        onClick = {
                                            score = elem
                                        })
                                    Text(text = elem.toString())
                                }
                            }
                        }
                    }
                }
                Configuration.ORIENTATION_LANDSCAPE -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(text = stringResource(id = R.string.score))
                        Spacer(modifier = Modifier.padding(36.dp))
                        Row {
                            scoreOptions.forEachIndexed {index, elem ->
                                if (index <= 5){
                                    Column(
                                        modifier = Modifier.selectable(selected = (elem == score),
                                            onClick = { score = elem })
                                    ) {
                                        Text(text = elem.toString())
                                        RadioButton(selected = (score == elem),
                                            onClick = {
                                                score = elem
                                            }
                                        )
                                    }
                                }
                            }
                        }
                        Row {
                            scoreOptions.forEachIndexed {index, elem ->
                                if (index > 5){
                                    Column(
                                        modifier = Modifier
                                            .selectable(selected = (elem == score),
                                                onClick = { score = elem })
                                    ) {
                                        RadioButton(selected = (score == elem),
                                            onClick = {
                                                score = elem
                                            })
                                        Text(text = elem.toString())
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
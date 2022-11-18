package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.myapplication.ui.screens.allroutines.RoutinesViewModel

@Composable
fun ScorePopup(
    onPopupDismissed: () -> Unit,
    routinesViewModel: RoutinesViewModel,
    myRoutineId: Int
) {
    var score by remember { mutableStateOf(0) }
    val scoreOptions = arrayOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    AlertDialog(
        onDismissRequest = onPopupDismissed,
        confirmButton = {
            Button(onClick = {
                routinesViewModel.setScore(myRoutineId, score, "")
//                myRoutine.id?.let { routinesViewModel.setScore(it, score, null) }
            }) {
                Text(text = stringResource(id = R.string.apply))
            }
        }, text = {
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
    )
}
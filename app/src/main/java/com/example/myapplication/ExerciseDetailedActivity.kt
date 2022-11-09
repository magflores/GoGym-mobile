package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.Green200
import com.example.myapplication.ui.theme.Grey
import com.example.myapplication.ui.theme.LightGreen
import com.example.myapplication.ui.theme.Shapes

@Composable
fun ExerciseDetailedTopBar() {
    TopAppBar(
        title = { Text(text = "tortura china") },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        backgroundColor = Color.White
    )
}

@Composable
fun ExerciseInfo() {
    Column(modifier = Modifier.fillMaxWidth().background(color = Grey).height(150.dp)) {
        Text(text = "@juancito", color = Color.White)
    }
}

@Composable
fun ExerciseCard() {
    Card(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 50.dp
        ).fillMaxWidth().defaultMinSize(minHeight = 100.dp),
        backgroundColor = LightGreen,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Exercise",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Composable
fun ExerciseDetailedContent() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ExerciseInfo()
        ExerciseCard()
    }
}

@Preview
@Composable
fun ExerciseDetailed() {
    Scaffold(
        topBar = { ExerciseDetailedTopBar() },
        content = { ExerciseDetailedContent() }
    )
}

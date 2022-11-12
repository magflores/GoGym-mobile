package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DetailedRoutine(navController: NavController, routineId: Int, onBack: () -> Unit) {
    BottomNavLayout(navController = navController) { bottomNavPadding ->
        Scaffold(topBar = {
            RoutineTopBar(onBack)
        }, floatingActionButton = {
            IconButton(onClick = { /*TODO*/ }) {
                Icons.Filled.PlayArrow
            }
        }, modifier = Modifier.padding(bottomNavPadding)
        ) { padding ->
            LazyColumn(
                modifier = Modifier.padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                this.item { RoutineInfoCard(modifier = Modifier.padding(padding)) }
                this.item {
                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                            .padding(padding)
                    )
                }
                this.items(items = getRoutineCycles()) {
                    RoutineCycleCard(it)
                }
            }
        }
    }
}

@Composable
private fun RoutineCycleCard(cycle: RoutineCycle) {
    Card(
        backgroundColor = Color.LightGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "${cycle.title} Repeat: ${cycle.repeats}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${cycle.repeats}", fontSize = 16.sp
            )
            for (exercise in cycle.exercises) {
                Text(
                    text = exercise, fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun RoutineInfoCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Routine title", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                Text(text = "Routine detail", fontWeight = FontWeight.Light, fontSize = 20.sp)

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo2docuatri),
                    contentDescription = "Author profile picture",
                    modifier = Modifier.size(80.dp)
                )
                Column {
                    Text(text = "Author")
                    Text(text = "Last online")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "creation date")
                Text(text = "Score")
                Text(text = "difficulty")
                Text(text = "Category")
            }
        }
    }
}

class RoutineCycle(
    val id: Int, val title: String, val repeats: Int, val exercises: List<String>
)

fun getRoutineCycles() = (1..10).map {
    RoutineCycle(
        id = it,
        title = "Cycle $it",
        repeats = it,
        exercises = listOf("Exercise 1", "Exercise 2", "Exercise 3")
    )
}

@Composable
fun RoutineTopBar(onBack: () -> Unit) {
    TopAppBar(title = {
        Text("Routine")
    }, navigationIcon = {
        IconButton(onClick = onBack) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
    }, actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Menu, contentDescription = "Menu")
        }
    })
}


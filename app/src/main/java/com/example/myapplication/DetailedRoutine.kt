package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailedRoutine(navController: NavController, routineId: Int, onBack: () -> Unit) {
    BottomNavLayout(navController = navController) {
        Scaffold(
            topBar = {
                RoutineTopBar(onBack)
            },
            floatingActionButton = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icons.Filled.PlayArrow
                }
            }
        ) {
            Column() {
                RoutineInfoCard()
                Spacer(modifier = Modifier.height(16.dp))
                RoutineCycles()
            }
        }
    }

}

@Composable
fun RoutineInfoCard() {
    Surface(
        modifier = Modifier.fillMaxWidth()
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
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
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

@Composable
fun RoutineCycles() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "this should be a cycle with exercises inside?")
    }
}

@Composable
fun RoutineTopBar(onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text("Routine")
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
    )
}


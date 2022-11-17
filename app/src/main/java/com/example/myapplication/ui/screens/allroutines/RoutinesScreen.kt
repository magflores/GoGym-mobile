package com.example.myapplication.ui.screens.allroutines

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.AllRoutinesAppBar
import com.example.myapplication.data.model.Routine
import com.example.myapplication.ui.ExampleViewModel

@Composable
fun ListRow(model: Routine) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 5.dp),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Row {
                Column(
                    modifier = Modifier.fillMaxWidth(0.5F),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        modifier = Modifier.padding(5.dp, 0.dp),
                        text = model.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier.padding(10.dp, 10.dp),
                        text = model.detail,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier.padding(10.dp, 10.dp),
                        text = model.difficulty,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
//                    Spacer(modifier = Modifier.width(26.dp))
                Column(
                    modifier = Modifier
                        .padding(5.dp, 0.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                )
                {
                    Text(
                        modifier = Modifier.padding(5.dp, 0.dp),
                        text = model.score.toString(), // TODO como mostrar esto bien
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier.padding(10.dp, 10.dp),
                        text = model.category,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun RutinesScreen(
    padding: PaddingValues,
    routinesViewModel: RoutinesViewModel,
    mainViewModel: ExampleViewModel
) {

    /*
    BottomNavLayout(navController = navController) { bottomNavPadding ->
        Scaffold(
            topBar = {RoutineTopBar(onBack)},
            modifier = Modifier.padding(bottomNavPadding)
        ) { padding ->
            val configuration = LocalConfiguration.current
            val myVar = false // TODO boton para cambiar vista
            if (myVar) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement
                        .spacedBy(4.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    modifier = Modifier
                        .padding(padding)
                        .background(Color.White)
                ) {
                    items(rutineList) { model ->
                        ListRow(model = model)
                    }
                }
            } else {
                when (configuration.orientation){
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(200.dp),
                            verticalArrangement = Arrangement
                                .spacedBy(4.dp),
                            contentPadding =
                            PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            modifier = Modifier
                                .padding(padding)
                                .background(Color.White)
                        ) {
                            items(rutineList) { model ->
                                ListRow(model = model)
                            }
                        }
                    }
                    Configuration.ORIENTATION_PORTRAIT -> {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(150.dp),
                            verticalArrangement = Arrangement
                                .spacedBy(4.dp),
                            contentPadding =
                            PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            modifier = Modifier
                                .padding(padding)
                                .background(Color.White)
                        ) {
                            items(rutineList) { model ->
                                ListRow(model = model)
                            }
                        }
                    }
                    Configuration.ORIENTATION_UNDEFINED -> {
                        TODO()
                    }
                }
            }
        }
    }
    */

    LaunchedEffect(Unit) {
        routinesViewModel.getAllRoutines()
    }
    val uiState = routinesViewModel.uiState
    val typeView = true // TODO boton para cambiar vista
    RoutinesLayout(typeView, padding, uiState, "ALL ROUTINES", mainViewModel)
}

@Composable
fun RoutinesLayout(
    typeView: Boolean,
    padding: PaddingValues,
    uiState: RoutinesUiState,
    title: String,
    mainViewModel: ExampleViewModel
) {
    Scaffold(
        topBar = {
            AllRoutinesAppBar(title = title, typeView, mainViewModel)
        },
        modifier = Modifier.padding(padding)
    ) {
        val configuration = LocalConfiguration.current
        if (typeView) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement
                    .spacedBy(4.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier
                    .background(Color.White)
            ) {
                items(uiState.routines) { model ->
                    ListRow(model = model)
                }
            }
        } else {
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(200.dp),
                        verticalArrangement = Arrangement
                            .spacedBy(4.dp),
                        contentPadding =
                        PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        modifier = Modifier
                            .background(Color.White)
                    ) {
                        items(uiState.routines) { model ->
                            ListRow(model = model)
                        }
                    }
                }
                Configuration.ORIENTATION_PORTRAIT -> {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(150.dp),
                        verticalArrangement = Arrangement
                            .spacedBy(4.dp),
                        contentPadding =
                        PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        modifier = Modifier
                            .padding(it)
                            .background(Color.White)
                    ) {
                        items(uiState.routines) { model ->
                            ListRow(model = model)
                        }
                    }
                }
                Configuration.ORIENTATION_UNDEFINED -> {
                    TODO()
                }
                Configuration.ORIENTATION_SQUARE -> {
                    TODO()
                }
            }
        }
    }
}

@Composable
fun UserRoutinesScreen(
    padding: PaddingValues,
    routinesViewModel: RoutinesViewModel,
    exampleViewModel: ExampleViewModel
) {
    LaunchedEffect(Unit) {
        exampleViewModel.getCurrentUser().invokeOnCompletion {
            exampleViewModel.uiState.currentUser?.let {
                it.id?.let { id ->
                    routinesViewModel.getUserRoutines(id)
                }
            }
        }
    }
    if (exampleViewModel.uiState.currentUser == null || routinesViewModel.uiState.routines.isEmpty()) {
        Text(text = "Loading")
        return
    }
    val uiState = routinesViewModel.uiState
    val typeView = true
    RoutinesLayout(
        typeView = typeView,
        padding = padding,
        uiState = uiState,
        title = "MY ROUTINES",
        mainViewModel = exampleViewModel
    )
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreviewRutinesScreen() {
//    MyApplicationTheme {
//        RutinesScreen()
//    }
//}
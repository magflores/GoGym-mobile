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
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.AllRoutinesAppBar
import com.example.myapplication.R
import com.example.myapplication.SortPopup
import com.example.myapplication.data.model.Routine
import com.example.myapplication.ui.ExampleViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListRow(model: Routine, onGoToRoutine: (Int) -> Unit) {
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
            backgroundColor = MaterialTheme.colors.primary,
            onClick = { model.id?.let { onGoToRoutine(it) } }
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
    mainViewModel: ExampleViewModel,
    onGoToRoutine: (Int) -> Unit
) {


    LaunchedEffect(Unit) {
        routinesViewModel.getAllRoutines()
    }
    val uiState = routinesViewModel.uiState
    RoutinesLayout(
        padding = padding,
        uiState = uiState,
        viewModel = routinesViewModel,
        title = stringResource(id = R.string.all_routines),
        mainViewModel = mainViewModel,
        onGoToRoutine = onGoToRoutine
    )
}

@Composable
fun RoutinesLayout(
    padding: PaddingValues,
    uiState: RoutinesUiState,
    viewModel: RoutinesViewModel,
    title: String,
    mainViewModel: ExampleViewModel,
    onGoToRoutine: (Int) -> Unit
) {
    var showPopup by remember{ mutableStateOf(false) }
    val onPopupDismissed = {showPopup = false}
    val onSortClick = {showPopup = true}
    Scaffold(
        topBar = {
            AllRoutinesAppBar(
                title = title,
                mainViewModel = mainViewModel,
                viewModel = viewModel,
                onSortClick = onSortClick
            )
        },
        modifier = Modifier.padding(padding)
    ) {
        val configuration = LocalConfiguration.current
        if (uiState.stateTypeOfView_List_Grid) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement
                    .spacedBy(4.dp),
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp),
                modifier = Modifier
                    .background(Color.White)
            ) {
                items(uiState.routines) { model ->
                    ListRow(model = model, onGoToRoutine)
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
                        PaddingValues(
                            horizontal = 16.dp,
                            vertical = 8.dp),
                        modifier = Modifier
                            .background(Color.White)
                    ) {
                        items(uiState.routines) { model ->
                            ListRow(model = model, onGoToRoutine)
                        }
                    }
                }
                Configuration.ORIENTATION_PORTRAIT -> {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(150.dp),
                        verticalArrangement = Arrangement
                            .spacedBy(4.dp),
                        contentPadding =
                        PaddingValues(
                            horizontal = 16.dp,
                            vertical = 8.dp),
                        modifier = Modifier
                            .padding(it)
                            .background(Color.White)
                    ) {
                        items(uiState.routines) { model ->
                            ListRow(model = model, onGoToRoutine)
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

    if (showPopup) {
        SortPopup(onPopupDismissed,
            routinesViewModel = viewModel)
    }
}

@Composable
fun UserRoutinesScreen(
    padding: PaddingValues,
    routinesViewModel: RoutinesViewModel,
    exampleViewModel: ExampleViewModel,
    onGoToRoutine: (Int) -> Unit
) {
//    LaunchedEffect(Unit) {
//        exampleViewModel.getCurrentUser().invokeOnCompletion {
//            exampleViewModel.uiState.currentUser?.let {
//                it.id?.let { id ->
//                    routinesViewModel.getUserRoutines(id)
//                }
    LaunchedEffect(exampleViewModel.uiState.currentUser) {
        exampleViewModel.uiState.currentUser?.let {
            it.id?.let { id ->
                routinesViewModel.getUserRoutines(id)
            }
        }
    }
    val uiState = routinesViewModel.uiState
//    RoutinesLayout(
//        padding = padding,
//        uiState = uiState,
//        title = "MY ROUTINES",
//        mainViewModel = exampleViewModel,
//        onGoToRoutine = onGoToRoutine
//    )

    RoutinesLayout(
        padding = padding,
        uiState = uiState,
        viewModel = routinesViewModel,
        title = stringResource(id = R.string.my_routines),
        mainViewModel = exampleViewModel,
        onGoToRoutine = onGoToRoutine
    )
}
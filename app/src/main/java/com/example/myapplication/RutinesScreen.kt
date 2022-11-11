package com.example.myapplication

import android.graphics.ColorSpace
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

data class RutineList(val title: String, val subTitle: String)

@Composable
fun ListRow(model: RutineList){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp,5.dp),
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(5.dp,0.dp),
                        text = model.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier.padding(10.dp,10.dp),
                        text = model.subTitle,
                        fontSize = 24.sp,
//            fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }
//        }
    }
}

private val rutineList = mutableListOf<RutineList>()

@Composable
fun RutinesScreen(){
    rutineList.add(RutineList("Rutina 1", "by pepe"))
    rutineList.add(RutineList("Rutina 2", "by pepe"))
    rutineList.add(RutineList("Rutina 3", "by pepe"))
    rutineList.add(RutineList("Rutina 4", "by pepe"))
    rutineList.add(RutineList("Rutina 1", "by santi-trainer12"))
    rutineList.add(RutineList("Rutina 2", "by santi-trainer12"))
    rutineList.add(RutineList("Rutina 3", "by santi-trainer12"))
    rutineList.add(RutineList("Rutina 4", "by santi-trainer12"))
    rutineList.add(RutineList("Rutina 1", "by juan"))
    rutineList.add(RutineList("Rutina 2", "by juan"))
    rutineList.add(RutineList("Rutina 3", "by juan"))
    rutineList.add(RutineList("Rutina 4", "by juan"))
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(4.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ){
            items(rutineList) {
                model -> ListRow(model = model)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewRutinesScreen() {
    MyApplicationTheme {
        RutinesScreen()
    }
}
package com.example.myapplication

import android.graphics.ColorSpace
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

data class RutineList(val title: String, val subTitle: String, val score: Float,
                      val difficulty: String, val category: String)

@Composable
fun ListRow(model: RutineList){
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
                            modifier = Modifier.padding(5.dp,0.dp),
                            text = model.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(10.dp,10.dp),
                            text = model.subTitle,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(10.dp,10.dp),
                            text = model.difficulty,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
//                    Spacer(modifier = Modifier.width(26.dp))
                    Column(
                        modifier = Modifier.padding(5.dp, 0.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End)
                    {
                        Text(
                            modifier = Modifier.padding(5.dp,0.dp),
                            text = model.score.toString(), // TODO como mostrar esto bien
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(10.dp,10.dp),
                            text = model.category,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }
                }
            }
    }
}

private val rutineList = mutableListOf<RutineList>()

@Composable
fun RutinesScreen(){
    rutineList.add(RutineList("Rutina 1", "by pepe", 4.2F, "Easy", "Abs"))
    rutineList.add(RutineList("Rutina 2", "by pepe", 4.2F, "Easy", "Abs"))
    rutineList.add(RutineList("Rutina 3", "by pepe", 4.2F, "Easy", "Abs"))
    rutineList.add(RutineList("Rutina 4", "by pepe", 4.2F, "Easy", "Abs"))
    rutineList.add(RutineList("Rutina 1", "by santi-trainer12", 4.2F, "Easy", "Abs"))
    rutineList.add(RutineList("Rutina 2", "by santi-trainer12", 4.2F, "Easy", "Abs"))
    rutineList.add(RutineList("Rutina 3", "by santi-trainer12", 4.2F, "Easy", "Abs"))
    rutineList.add(RutineList("Rutina 4", "by santi-trainer12", 4.2F, "Easy", "Abs"))

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
package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                MyNavGraph(navController = navController)
            }
        }
    }
}


@Composable
fun MyApp(names: List<String> = listOf("Cami", "Magui", "Santi", "Juan")) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Row {
            for (name in names) {
                Greeting(name = name)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = "Hello, ",
            color = MaterialTheme.colors.secondary
        )
        Text(
            text = name,
            color = MaterialTheme.colors.primary
        )
        Text(text = "Bye")
    }
}

@Composable
fun StyledTextField(labelText: String) {
    var value by remember { mutableStateOf("") }

    TextField(
        value = value,
        onValueChange = { value = it },
        label = { Text(text = labelText) },
        maxLines = 1,
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
fun IconTitleSubTitle(title: String, mySubTitle: String) {
    Icon(
//        imageVector = Icons.Filled.Favorite,
//        painter = painterResource(id = R.drawable.ic_launcher_background),
        painter = painterResource(id = R.drawable.logo2docuatri),
        contentDescription = stringResource(id = R.string.logo),
//        stringResource(id = )
    )
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
//        TODO: style = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
//        modifier = Modifier.padding(16.dp)
    )
    Text(
        text = mySubTitle,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        color = MaterialTheme.colors.secondary
//        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun LogIn() {
//    TODO
//    val configuration = LocalConfiguration.current
//    when (configuration.orientation ){
//        Configuration.ORIENTATION_LANDSCAPE -> {}
//        else -> {}
//    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            horizontalArrangement = Arrangement.Center,
            // verticalAlignment = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconTitleSubTitle(title = "GoGym!",
                    mySubTitle = stringResource(id = R.string.log_in))

                StyledTextField(stringResource(id = R.string.e_mail_label_to_complete))
                StyledTextField(stringResource(id = R.string.password_label_to_complete))

                ButtonPlusTextBelow(
                    stringResource(id = R.string.enter_button),
                    stringResource(id  = R.string.forgot_password))
            }
        }
    }
}

@Composable
fun ButtonPlusTextBelow(buttonText: String, textBelow: String) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary)
    ) {
        Text(text = buttonText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
    // TODO link
    Text(text = textBelow,
//        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}

@Composable
fun JoinUs() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            horizontalArrangement = Arrangement.Center,
            // verticalAlignment = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                IconTitleSubTitle(title = "GoGym!", mySubTitle = "JoinUs")
                StyledTextField("NAME")
                StyledTextField("SURNAME")
                StyledTextField("E-MAIL")
                StyledTextField("BIRTHDAY")
                StyledTextField("PASSWORD")
                StyledTextField("CONFIRM PASSWORD")
                ButtonPlusTextBelow(buttonText = "REGISTER",
                    textBelow = "Already have an account? Log in here")
            }
        }
    }
}

@Composable
fun RutineCard(stringRutineName: String, stringAuthorRutine: String) {
//    TODO: no esta quedando bien el tamaño de la card con respecto a su contenido
    Card(shape = RoundedCornerShape(24.dp),
//        modifier = Modifier
//            .size(1000.dp, 80.dp) //TODO chequear pero creo que va bien este tamaño
//            .padding(0.dp, 26.dp)
//        ,
        backgroundColor = MaterialTheme.colors.primary) {
        Column(
//            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(color = Color.Black,
                modifier = Modifier.padding(5.dp),
                text = stringRutineName)
            Text(color = Color.Black,
                modifier = Modifier.padding(10.dp),
                text = stringAuthorRutine)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
//        JoinUs()
        LogIn()
//        RutineCard(stringRutineName = "holaholaholaholahola", stringAuthorRutine = "chau")
    }
}
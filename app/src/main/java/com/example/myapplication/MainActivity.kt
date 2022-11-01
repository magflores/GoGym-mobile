package com.example.myapplication

import android.icu.text.AlphabeticIndex
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Orange200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MyApp()
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
        // textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
fun IconTitleSubTitle(title: String, mySubTitle: String) {
    Icon(
// TODO
        imageVector = Icons.Filled.Favorite,
        contentDescription = "TODO",
    )
    Text(
        text = title,
//        modifier = Modifier.padding(16.dp)
    )
    Text(
        text = mySubTitle,
        color = MaterialTheme.colors.secondary
//        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun LogIn() {
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
                IconTitleSubTitle(title = "GoGym!",
                    mySubTitle = "Log In")
                StyledTextField("E-MAIL")
                StyledTextField("PASSWORD")
                ButtonPlusTextBelow(buttonText = "Enter", textBelow = "Forgot your password? Click here")
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
        Text(text = buttonText)
    }
    // TODO
    Text(text = textBelow)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
//        JoinUs()
        LogIn()
    }
}
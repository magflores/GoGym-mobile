package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.ExampleViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.util.getViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val exampleViewModel: ExampleViewModel = viewModel(factory = getViewModelFactory())
                exampleViewModel.getCurrentUser()
                MyNavGraph(navController = navController, exampleViewModel = exampleViewModel)
            }
        }
    }
}

@Composable
fun StyledTextFieldPassword(labelText: String, text: String, setText: (String) -> Unit){
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = text,
        onValueChange = { setText(it) },
        label = { Text(text = labelText) },
        maxLines = 1,
        modifier = Modifier.padding(20.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                painterResource(id = R.drawable.baseline_visibility_black_36)
            else painterResource(id = R.drawable.baseline_visibility_off_black_36)

            // Please provide localized description for accessibility services
            val description = if (passwordVisible) stringResource(id = R.string.hide_password) else stringResource(id = R.string.show_password)

            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(painter = image, description)
            }
        }
    )
}

@Composable
fun StyledTextField(labelText: String, text: String, setText: (String) -> Unit) {

    TextField(
        value = text,
        onValueChange = { setText(it) },
        label = { Text(text = labelText) },
        maxLines = 1,
        modifier = Modifier.padding(20.dp),
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
fun LogIn(viewModel: ExampleViewModel, onLogIn: () -> Unit) {
//    TODO
//    val configuration = LocalConfiguration.current
//    when (configuration.orientation ){
//        Configuration.ORIENTATION_LANDSCAPE -> {}
//        else -> {}
//    }
    val (username, setUsername) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

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

                StyledTextField(stringResource(id = R.string.e_mail_label_to_complete), username, setUsername)
                StyledTextFieldPassword(stringResource(id = R.string.password_label_to_complete), password, setPassword)

                ButtonPlusTextBelow(
                    stringResource(id = R.string.enter_button),
                    stringResource(id  = R.string.forgot_password), viewModel, username, password, onLogIn)
            }
        }
    }
}

@Composable
fun ButtonPlusTextBelow(buttonText: String, textBelow: String, viewModel: ExampleViewModel, username: String, password: String, onLogIn: () -> Unit) {
    Button(
        onClick = { viewModel.login(username, password, onLogIn) },
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

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MyApplicationTheme {
//        LogIn()
//    }
//}
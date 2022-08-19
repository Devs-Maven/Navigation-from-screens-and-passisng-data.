package com.example.newnavigation

import android.annotation.SuppressLint
import android.app.FragmentManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newnavigation.ui.theme.NewNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.HomeScreen.route)
                {
                    composable(route = Screens.HomeScreen.route){ HomeScreen(navController)}

                    // Here we are passing two data values for 2nd Screen which is id1 & id2.
                    composable(route = Screens.SecondScreen.route + "/{id1}" + "/{id2}"){ it->
                        val data1 = it.arguments?.getString("id1")!!
                        val data2 = it.arguments?.getString("id2")!!
                        SecondScreen(navController, data1, data2)
                    }
                }


        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

    @Composable
    fun HomeScreen(navController: NavController) {
        var data1 by remember{ mutableStateOf("")}
        var data2 by remember{ mutableStateOf("")}
        Scaffold(
            topBar = { TopAppBar(
                title = { Text("Second Screen") },
                navigationIcon = { IconButton(
                    onClick = { navController.popBackStack() },
                    content = { Icon(Icons.Default.ArrowBack, "") }
                )}
            ) },)
    {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                OutlinedTextField(
                    value = data1,
                    onValueChange = { value ->
                        data1 = value
                    }
                )

                Spacer(Modifier.height(10.dp))

                OutlinedTextField(
                    value = data2,
                    onValueChange = { value ->
                        data2 = value
                    }
                )

                Spacer(Modifier.height(20.dp))

                Button(onClick = {
                    // sending data through navController
                    navController.navigate(
                        Screens.SecondScreen.route + "/${data1}" + "/${data2}"
                    )
                }) { Text("Done", fontSize = 20.sp) }
            }
        }
    }

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SecondScreen(navController: NavController, data1: String, data2: String) {
    Scaffold(
        topBar = { TopAppBar(
            title = { Text("Second Screen") },
            navigationIcon = { IconButton(
                onClick = { navController.popBackStack() },
                content = { Icon(Icons.Default.ArrowBack, "") }
            )}
        ) },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Multiplication of $data1 & $data2 is ${data1.toInt() * data2.toInt()}",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}

package com.example.patrick_bautista_android_developer

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavActionBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.patrick_bautista_android_developer.ui.add_edit_todo.AddEditScreen
import com.example.patrick_bautista_android_developer.ui.theme.Patrick_Bautista_Android_DeveloperTheme
import com.example.patrick_bautista_android_developer.ui.todo_screeen.TodoScreen
import com.example.patrick_bautista_android_developer.ui.util.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Patrick_Bautista_Android_DeveloperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Route.TODO_SCREEN){
                        composable(route = Route.TODO_SCREEN){
                            TodoScreen(navController = navController)
                        }
                        composable(
                            route = Route.ADD_EDIT_SCREEN + "?TodoId={TodoId}",
                            arguments = listOf(
                                navArgument(
                                    name = "TodoId") {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    }
                                )
                        ){
                            AddEditScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Patrick_Bautista_Android_DeveloperTheme {
        Greeting("Android")
    }
}
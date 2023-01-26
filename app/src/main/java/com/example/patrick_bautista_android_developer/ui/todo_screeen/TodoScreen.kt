package com.example.patrick_bautista_android_developer.ui.todo_screeen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.patrick_bautista_android_developer.ui.theme.BackGroundColor1
import com.example.patrick_bautista_android_developer.ui.todo_screeen.component.NameFilterTextField
import com.example.patrick_bautista_android_developer.ui.todo_screeen.component.TodoItem
import com.example.patrick_bautista_android_developer.ui.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoScreen(
    viewModel: TodoScreenViewModel = hiltViewModel(),
    navController: NavController
){

    val todoItem = viewModel.todoItem
    val search = viewModel.search

    LaunchedEffect(key1 = 1){
        viewModel.uiState.collectLatest { value: UiEvent ->
            when(value){
                is UiEvent.OnNavigate -> {
                    navController.navigate(route = value.route)
                }
                else -> Unit
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackGroundColor1)){

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NameFilterTextField(
                text = search.value,
                placeholder = "",
                isPlaceholderVisible = false,
                onValueChange = {viewModel.onEvent(TodoScreenEvent.OnSearch(it))},
                onFocusChange = {},
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(60.dp)
                    .padding(top = 5.dp, bottom = 5.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.LightGray))


            Spacer(modifier = Modifier.height(50.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){
                items(todoItem.value){ item ->
                    TodoItem(
                        todo = item,
                        onCheck = {state -> viewModel.onEvent(TodoScreenEvent.OnDoneTodo(item.copy(isDone = state)))},
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(100.dp)
                            .border(width = 1.dp, color = Color.Black)
                            .clickable { viewModel.onEvent(TodoScreenEvent.OnSelectTodo(item)) })
                    
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }

        FloatingActionButton(
            onClick = {viewModel.onEvent(TodoScreenEvent.OnAddTodo)},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 30.dp, end = 30.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Todo")
        }

    }

}
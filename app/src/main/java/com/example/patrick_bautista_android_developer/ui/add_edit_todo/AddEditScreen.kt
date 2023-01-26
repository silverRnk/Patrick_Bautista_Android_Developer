package com.example.patrick_bautista_android_developer.ui.add_edit_todo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.patrick_bautista_android_developer.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEditScreen(
    viewModel: AddEditTodoViewModel = hiltViewModel(),
    navController: NavController
){
    val todo = viewModel.todo.value

    val dateTimeFormatter = DateTimeFormatter.ofPattern("MMM, dd yyyy hh:mm")

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = 1){
        viewModel.uiState.collectLatest { uiEvent ->
            when(uiEvent){
                is UiEvent.PopUpBackStack -> {
                    navController.popBackStack()
                }
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(uiEvent.message)
                }
            }
        }
    }
    
    Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {

        Column(
            modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(100.dp))
            
            Text(text = "Todo", style = MaterialTheme.typography.h1)
            TextField(
                value = todo.title,
                onValueChange = {viewModel.onEvent(AddEditEvent.OnTitleChange(it))},
                textStyle = MaterialTheme.typography.body1)

            Spacer(modifier = Modifier.height(20.dp))

            Text(text="Date", style = MaterialTheme.typography.h1)
            Text(text=dateTimeFormatter.format(todo.createdDate), style = MaterialTheme.typography.body1)

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "Ok",
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clickable { viewModel.onEvent(AddEditEvent.OnSave) })

                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clickable { viewModel.onEvent(AddEditEvent.OnCancel) })
            }
        }
    }


}
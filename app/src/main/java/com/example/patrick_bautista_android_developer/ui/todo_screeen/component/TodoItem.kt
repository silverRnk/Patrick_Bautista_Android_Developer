package com.example.patrick_bautista_android_developer.ui.todo_screeen.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.patrick_bautista_android_developer.data.Todo
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoItem(
    todo: Todo,
    onCheck: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    val dateTimeFormatter = DateTimeFormatter.ofPattern("MMM, dd yyyy hh:mm")

    Box(modifier = modifier){
        Row(modifier = Modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth(0.9f),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxHeight(0.7f)
                .padding(start = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = todo.title?: "",
                    style = MaterialTheme.typography.h1)

                Text(
                    text = dateTimeFormatter.format(todo.createdDate)?: "",
                    style = MaterialTheme.typography.h2)
            }

            Checkbox(checked = todo.isDone, onCheckedChange = {onCheck(it)})
        }


    }
}
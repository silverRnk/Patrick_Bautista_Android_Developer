package com.example.patrick_bautista_android_developer.ui.todo_screeen

import com.example.patrick_bautista_android_developer.data.Todo

sealed class TodoScreenEvent{
    data class OnSearch(val search: String): TodoScreenEvent()
    data class OnSelectTodo(val todo: Todo): TodoScreenEvent()
    data class OnDoneTodo(val todo: Todo): TodoScreenEvent()
    object OnAddTodo: TodoScreenEvent()
}

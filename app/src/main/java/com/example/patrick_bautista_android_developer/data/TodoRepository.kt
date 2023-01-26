package com.example.patrick_bautista_android_developer.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertToDo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    suspend fun getTodoById(id: Int): Todo?

    fun getTodos(search: String): Flow<List<Todo>>
}
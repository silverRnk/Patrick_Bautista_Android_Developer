package com.example.patrick_bautista_android_developer.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImp(
    private val dao: TodoDao): TodoRepository {
    override suspend fun insertToDo(todo: Todo) {
        dao.insertToDo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(search: String): Flow<List<Todo>> {
        return if (search.isEmpty()){
            dao.getTodos()
        }else{
            dao.getTodos().map {
                it.filter { item ->
                    item.title!!.lowercase().contains(Regex("\\S*$search\\S*"))
                }.sortedByDescending { item ->
                    item.createdDate
                }
            }
        }
    }
}
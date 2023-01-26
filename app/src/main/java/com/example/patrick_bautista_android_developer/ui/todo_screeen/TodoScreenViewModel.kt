package com.example.patrick_bautista_android_developer.ui.todo_screeen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patrick_bautista_android_developer.data.Todo
import com.example.patrick_bautista_android_developer.data.TodoRepository
import com.example.patrick_bautista_android_developer.ui.util.Route
import com.example.patrick_bautista_android_developer.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoScreenViewModel @Inject constructor(
    private val repository: TodoRepository): ViewModel() {

    private val _todoItem = mutableStateOf(listOf<Todo>())
    val todoItem: State<List<Todo>> = _todoItem

    private val _search = mutableStateOf("")
    val search: State<String> = _search

    private var getJob: Job? = null

    private val _uiState = MutableSharedFlow<UiEvent>()
    val uiState = _uiState.asSharedFlow()

    init {
        getTodoItems("")
    }

    fun onEvent(event: TodoScreenEvent){
        when(event){
            is TodoScreenEvent.OnSearch -> {
                _search.value = event.search
                getTodoItems(search.value)
            }
            is TodoScreenEvent.OnSelectTodo -> {
                viewModelScope.launch {
                    _uiState.emit(UiEvent.OnNavigate(route = Route.ADD_EDIT_SCREEN + "?TodoId=${event.todo.id}"))
                }

            }
            is TodoScreenEvent.OnAddTodo -> {
                viewModelScope.launch {
                    _uiState.emit(UiEvent.OnNavigate(route = Route.ADD_EDIT_SCREEN))
                }
            }
            is TodoScreenEvent.OnDoneTodo -> {
                viewModelScope.launch {
                    repository.insertToDo(event.todo)
                }
            }
        }
    }

    private fun getTodoItems(search: String){
        getJob?.cancel()
        getJob = repository.getTodos(search).onEach {
            _todoItem.value = it
        }.launchIn(viewModelScope)
    }
}
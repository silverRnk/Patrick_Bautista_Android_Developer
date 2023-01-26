package com.example.patrick_bautista_android_developer.ui.add_edit_todo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patrick_bautista_android_developer.data.Todo
import com.example.patrick_bautista_android_developer.data.TodoRepository
import com.example.patrick_bautista_android_developer.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _todo = mutableStateOf<Todo>(
        Todo(title = "", createdDate = LocalDateTime.now(), isDone = false)
    )
    val todo: State<Todo> = _todo

    private val _uiState = MutableSharedFlow<UiEvent>()
    val uiState = _uiState.asSharedFlow()

    init {
        val id = savedStateHandle.get<Int>("TodoId")
        if (id != -1 && id != null){
            viewModelScope.launch {
                repository.getTodoById(id)?.let {
                    _todo.value = it
                }
            }
        }
    }

    fun onEvent(event: AddEditEvent){
        when(event){
            is AddEditEvent.OnTitleChange -> {
                _todo.value = todo.value?.copy(title = event.title)
            }
            is AddEditEvent.OnSave -> {
                viewModelScope.launch {
                    if (todo.value.title.isNullOrEmpty()){
                        _uiState.emit(UiEvent.ShowSnackBar("Title Cannot Be Empty"))
                    }else{
                        repository.insertToDo(todo.value)
                        _uiState.emit(UiEvent.PopUpBackStack)
                    }
                }
            }
            is AddEditEvent.OnCancel -> {
                viewModelScope.launch{
                    _uiState.emit(UiEvent.PopUpBackStack)
                }

            }
        }
    }
}
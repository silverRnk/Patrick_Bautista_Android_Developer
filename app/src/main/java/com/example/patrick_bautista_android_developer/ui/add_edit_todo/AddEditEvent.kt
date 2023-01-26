package com.example.patrick_bautista_android_developer.ui.add_edit_todo

sealed class AddEditEvent {
    data class OnTitleChange(val title: String): AddEditEvent()
    object OnSave: AddEditEvent()
    object OnCancel: AddEditEvent()
}
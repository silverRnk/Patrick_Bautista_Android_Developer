package com.example.patrick_bautista_android_developer.ui.util

sealed class UiEvent{
    data class OnNavigate(val route: String): UiEvent()
    data class ShowSnackBar(val message: String): UiEvent()
    object PopUpBackStack: UiEvent()
}

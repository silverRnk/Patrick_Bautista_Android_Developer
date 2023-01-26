package com.example.patrick_bautista_android_developer.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Todo(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val createdDate: LocalDateTime,
    val isDone: Boolean,

)

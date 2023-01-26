package com.example.patrick_bautista_android_developer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Todo::class],
    version = 1
)

@TypeConverters(TodoTypeConverter::class)
abstract class TodoDatabase: RoomDatabase() {

    abstract val dao: TodoDao
}
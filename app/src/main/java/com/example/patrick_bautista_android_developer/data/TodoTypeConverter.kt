package com.example.patrick_bautista_android_developer.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class TodoTypeConverter {

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLongDateToLocalDateTime(date: Long?): LocalDateTime?{
        return if (date == null) null else LocalDateTime.ofInstant(Instant.ofEpochSecond(date), ZoneId.of("UTC"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalDateTimeToLongDate(date: LocalDateTime?): Long?{
        return date?.toEpochSecond(ZoneOffset.UTC)
    }
}
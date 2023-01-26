package com.example.patrick_bautista_android_developer.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.patrick_bautista_android_developer.data.TodoDatabase
import com.example.patrick_bautista_android_developer.data.TodoRepository
import com.example.patrick_bautista_android_developer.data.TodoRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesDataBase(app:Application):TodoDatabase{
        return Room
            .databaseBuilder(app, TodoDatabase::class.java, "TodoDB")
            .build()
    }

    @Provides
    @Singleton
    fun providesTodoRepository(db: TodoDatabase): TodoRepository{
        return TodoRepositoryImp(db.dao)
    }
}
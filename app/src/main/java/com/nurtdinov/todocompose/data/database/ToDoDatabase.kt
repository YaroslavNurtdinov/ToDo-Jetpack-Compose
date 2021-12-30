package com.nurtdinov.todocompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nurtdinov.todocompose.data.models.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao
}
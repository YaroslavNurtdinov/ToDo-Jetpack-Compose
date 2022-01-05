package com.nurtdinov.todocompose.data

import com.nurtdinov.todocompose.data.database.ToDoDao
import com.nurtdinov.todocompose.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val toDoDao: ToDoDao) {

    val getAllTask: Flow<List<ToDoTask>> = toDoDao.getAllTasks()
    fun sortByLowPriority(): Flow<List<ToDoTask>> = toDoDao.sortByLowPriority()
    fun sortByHighPriority(): Flow<List<ToDoTask>> = toDoDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int): Flow<ToDoTask> {
        return toDoDao.getSelectedTask(taskId = taskId)
    }

    suspend fun addTask(toDoTask: ToDoTask) {
        return toDoDao.addTask(toDoTask)
    }

    suspend fun updateTask(toDoTask: ToDoTask) {
        toDoDao.updateTask(toDoTask)
    }

    suspend fun deleteTask(toDoTask: ToDoTask) {
        return toDoDao.deleteTask(toDoTask)
    }

    suspend fun deleteAll() {
        return toDoDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>> {
        return toDoDao.searchDatabase(searchQuery)
    }

}
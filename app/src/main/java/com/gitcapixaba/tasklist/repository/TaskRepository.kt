package com.gitcapixaba.tasklist.repository

import com.gitcapixaba.tasklist.datasource.DataSource
import com.gitcapixaba.tasklist.model.Task

class TaskRepository() {

    private val dataSource = DataSource()

    fun saveTaskRepository(task: String, description: String, priority: Int) {
        dataSource.saveTask(task, description, priority)
    }

    fun recoveryTask(): kotlinx.coroutines.flow.Flow<MutableList<Task>>{
        return dataSource.recoveryTask()
    }

    fun deleteTask(task: String){
        dataSource.deleteTask(task)
    }




}
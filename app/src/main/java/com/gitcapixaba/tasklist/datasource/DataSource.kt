package com.gitcapixaba.tasklist.datasource

import com.gitcapixaba.tasklist.model.Task
import com.gitcapixaba.tasklist.view.TaskListView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource {

    private val db = FirebaseFirestore.getInstance()

    private val _allTasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
    private val allTasks : StateFlow<MutableList<Task>> = _allTasks

    fun saveTask(task: String, description: String, priority: Int) {

        val taskMap =
            hashMapOf("task" to task, "description" to description, "priority" to priority)

        db.collection("tasks").document(task).set(taskMap).addOnCompleteListener {

        }.addOnFailureListener {

        }

    }

    fun recoveryTask() : Flow<MutableList<Task>> {

        val taskList: MutableList<Task> = mutableListOf()

        db.collection("tasks").get().addOnCompleteListener { querySnapshot ->
            if (querySnapshot.isSuccessful){
                for (document in querySnapshot.result){
                    val task = document.toObject(Task::class.java)
                    taskList.add(task)
                    _allTasks.value = taskList
                }
            }
        }

        return allTasks
    }

    fun deleteTask(task: String){
        db.collection("tasks").document(task).delete().addOnCompleteListener {

        }.addOnFailureListener {

        }
    }

}
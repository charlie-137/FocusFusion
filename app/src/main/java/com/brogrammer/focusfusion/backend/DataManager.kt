package com.brogrammer.focusfusion.backend

import android.content.Context
import com.brogrammer.focusfusion.model.TaskModel
import com.brogrammer.focusfusion.utilities.Utils

// Implement the update functionality

object DataManager {

    private val taskList = ArrayList<TaskModel>()

    fun addTask(taskName: String?, totalTaskDuration: Int?, iconName:String?) {
        val id = taskList.size + 1
        val task = TaskModel(id, taskName, totalTaskDuration, iconName,0)
        taskList.add(task)
    }

    fun initializeData() {
        taskList.add(TaskModel(1,"Task1",60,"Timelapse",30))
        taskList.add(TaskModel(2,"Task2",30,"Fitness",10))
    }

    fun getTaskList(): ArrayList<TaskModel>
    {
        return taskList
    }

    fun getTaskById(taskId: Int): TaskModel? {
        return taskList.find { it.id == taskId }
    }

    fun updateTask(taskId: Int, taskName: String?, totalTaskDuration: Int?, iconName:String?, context: Context) {
        Utils.showToast(context,iconName.toString())
        val taskToUpdate = taskList.find { it.id == taskId }
        taskToUpdate?.apply {
            taskName?.let { this.taskName = it }
            totalTaskDuration?.let { this.totalTaskDuration = it }
            iconName?.let { this.iconName = it }
        }
    }

    fun deleteTask(taskId: Int) {
        taskList.removeAll { it.id == taskId }
    }

}



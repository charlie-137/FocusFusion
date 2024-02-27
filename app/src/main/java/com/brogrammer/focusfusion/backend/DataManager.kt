package com.brogrammer.focusfusion.backend

import com.brogrammer.focusfusion.model.TaskModel

object DataManager {

    private val taskList = ArrayList<TaskModel>()

    fun addTask(taskName: String?, totalTaskDuration: Int?, iconName:String?) {
        val id = taskList.size + 1
        val task = TaskModel(1, taskName, totalTaskDuration, iconName,0)
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


}
package com.brogrammer.focusfusion.model

data class TaskModel(
    val id: Int,
    val taskName: String?,
    val totalTaskDuration: Int?,
    val iconName: String?,
    val completedTaskDuration: Int
)


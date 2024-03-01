package com.brogrammer.focusfusion.model

data class TaskModel(
    val id: Int,
    var taskName: String?,
    var totalTaskDuration: Int?,
    var iconName: String?,
    var completedTaskDuration: Int,
    var playPauseState: Boolean = false
)


package com.brogrammer.focusfusion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.brogrammer.focusfusion.backend.DataManager
import com.brogrammer.focusfusion.databinding.FragmentAddTaskBinding
import com.brogrammer.focusfusion.dialogs.IconDialog
import com.brogrammer.focusfusion.utilities.Utils
import androidx.navigation.fragment.findNavController
import com.brogrammer.focusfusion.model.TaskModel
import com.brogrammer.focusfusion.utilities.Constants
import viewmodel.TaskViewModel

class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private val viewModel: TaskViewModel by viewModels() // Initialize the viewModel property

    private val defaultIconName = Constants.allIconsNameToDrawable.keys.first() // Get the first icon name
    private val defaultIconDrawableId = Constants.allIconsNameToDrawable[defaultIconName] ?: 0 // Get the drawable id for the first icon

    var selectedIconName:String? = defaultIconName


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAddTaskBinding.inflate(layoutInflater)


        val taskId = arguments?.getInt("taskId")
        Utils.showToast(requireContext(),taskId.toString())

        val task = taskId?.let { DataManager.getTaskById(it) }

        task?.let {
            binding.taskName.setText(it.taskName)
            binding.duration.setText(it.totalTaskDuration.toString())
            val icon =  Constants.getIconDrawableRes(it.iconName!!)
            selectedIconName = it.iconName
            binding.iconImageView.setImageResource(icon!!)
        }

        binding.iconBtn.setOnClickListener {
            showIconDialog()
        }

        binding.saveBtn.setOnClickListener {
            saveTaskBtn()
            closeTaskBtn()
        }

        binding.closeBtn.setOnClickListener {
            closeTaskBtn()
        }


        return binding.root
    }


    private fun closeTaskBtn() {

        findNavController().navigateUp()
    }


    private fun saveTaskBtn() {
        val taskName = binding.taskName.text.toString()
        val durationString = binding.duration.text.toString()

        if (taskName.isNotEmpty() && durationString.isNotEmpty() && selectedIconName != null) {
            val duration = durationString.toIntOrNull() ?: 0
            val taskId = arguments?.getInt("taskId")

            if(taskId!=null)
            {
                val task = taskId.let { DataManager.getTaskById(it) }
                val originalIconName = task?.iconName

                if(selectedIconName != originalIconName)
                {
                    // Icon has been changed, update the task with the new icon
                    DataManager.updateTask(taskId, taskName, duration, selectedIconName, requireContext())
                }else{
                    // Icon remains unchanged, update the task without changing the icon
                    DataManager.updateTask(taskId, taskName, duration, originalIconName, requireContext())
                }

                // Task Id is provided, Update the existing task
//                DataManager.updateTask(taskId, taskName, duration, selectedIconName, requireContext())
                return
            }

            DataManager.addTask(taskName, duration, selectedIconName)
//            DataManager.taskList.add(TaskModel(1,"Task1",60,"Timelapse",30))
        } else {
            // Handle case when task name or duration is empty
            // For example, show an error message to the user
            Toast.makeText(requireContext(), "Task name or duration is empty", Toast.LENGTH_SHORT).show()
        }

        // Reset current playing position when adding a new task
        viewModel.resetCurrentPlayingPosition()

    }


    private fun showIconDialog() {
        val iconDialog = IconDialog(requireContext(), object : IconDialog.OnIconSelectedListener {
            override fun onIconSelected(iconName:String, iconDrawableId: Int) {
                // Handle selected icon
//                val iconName = icons.iconName
                selectedIconName = iconName
                binding.iconImageView.setImageResource(iconDrawableId)
            }
        })
        iconDialog.show()
    }

}
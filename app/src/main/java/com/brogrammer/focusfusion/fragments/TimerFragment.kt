package com.brogrammer.focusfusion.fragments

import CustomSpinnerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brogrammer.focusfusion.R
import com.brogrammer.focusfusion.backend.DataManager
import com.brogrammer.focusfusion.databinding.FragmentTimerBinding
import com.brogrammer.focusfusion.model.TaskModel
import com.brogrammer.focusfusion.utilities.Utils

class TimerFragment : Fragment() {

    private lateinit var binding: FragmentTimerBinding
    private lateinit var taskList: ArrayList<TaskModel>

    private var selectedItemPosition: Int = -1



    override fun onResume() {
        super.onResume()
        setUpDropDownMenu()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTimerBinding.inflate(layoutInflater)

//        if (DataManager.getTaskList().isEmpty()) {
//            DataManager.initializeData()
//        }
//
//        taskList = DataManager.getTaskList()
//
//        // Create an adapter for the spinner
//        val adapter = CustomSpinnerAdapter(
//            requireContext(),
//            R.layout.task_dropdown_item,
//            R.id.textViewItem,
//            taskList
//        )
//
//        // Set the adapter to the spinner
//        binding.taskSpinner.setAdapter(adapter)
//
//        // Set the default text for the spinner
//        binding.taskSpinner.setText("Select Task", false)
//
//        // Set item click listener for the spinner
//        binding.taskSpinner.setOnItemClickListener { parent, view, position, id ->
//            // Get the selected item
//            val selectedItem = taskList[position]
//            // Update the text of the spinner with the selected item name
//            binding.taskSpinner.setText(selectedItem.taskName, false)
//        }
//
//        binding.toastList.setOnClickListener {
//            val taskList = DataManager.getTaskList()
//            taskList.forEach { task ->
//                val taskName = task.taskName ?: "Unnamed Task"
//                val duration = task.totalTaskDuration ?: 0
//                val iconName = task.iconName ?: "Unknown Icon"
//                val toastMessage = "Task Name: $taskName, Duration: $duration Icon Name: $iconName"
//                Utils.showToast(requireContext(), toastMessage)
//            }
//        }

        return binding.root
    }

    private fun setUpDropDownMenu(){

        if (DataManager.getTaskList().isEmpty()) {
            DataManager.initializeData()
        }

        taskList = DataManager.getTaskList()

        // Create an adapter for the spinner
        val adapter = CustomSpinnerAdapter(
            requireContext(),
            R.layout.task_dropdown_item,
            R.id.textViewItem,
            taskList
        )

        // Set the adapter to the spinner
        binding.taskSpinner.setAdapter(adapter)

        // Set the default text for the spinner
        binding.taskSpinner.setText("Select Task", false)

        // Set item click listener for the spinner
        binding.taskSpinner.setOnItemClickListener { parent, view, position, id ->
            // Get the selected item
            val selectedItem = taskList[position]
            // Update the text of the spinner with the selected item name
            binding.taskSpinner.setText(selectedItem.taskName, false)
            binding.dailyGoal.text = selectedItem.totalTaskDuration.toString()
            binding.completedGoal.text = selectedItem.completedTaskDuration.toString()

            // Store the selected item position
            selectedItemPosition = position

        }

        // Set the selected item if it has been previously selected
        if (selectedItemPosition != -1) {
            binding.taskSpinner.setText(taskList[selectedItemPosition].taskName, false)
        }

        binding.toastList.setOnClickListener {
            val taskList = DataManager.getTaskList()
            taskList.forEach { task ->
                val taskName = task.taskName ?: "Unnamed Task"
                val duration = task.totalTaskDuration ?: 0
                val iconName = task.iconName ?: "Unknown Icon"
                val toastMessage = "Task Name: $taskName, Duration: $duration Icon Name: $iconName"
                Utils.showToast(requireContext(), toastMessage)
            }
        }

    }

}


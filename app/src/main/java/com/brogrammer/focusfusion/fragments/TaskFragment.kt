package com.brogrammer.focusfusion.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brogrammer.focusfusion.R
import com.brogrammer.focusfusion.activities.MainActivity
import com.brogrammer.focusfusion.adapters.TaskListAdapter
import com.brogrammer.focusfusion.backend.DataManager
import com.brogrammer.focusfusion.databinding.FragmentTaskBinding
import com.brogrammer.focusfusion.model.TaskModel
import com.brogrammer.focusfusion.utilities.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView


class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var navController: NavController // Define NavController variable


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTaskBinding.inflate(layoutInflater)
        navController = findNavController()
//        addTaskFragment()
//        DataManager.initializeData()

        displayAllTasks()


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addTaskFab.setOnClickListener {

            // Delay navigation until the ripple animation completes
            Handler(Looper.getMainLooper()).postDelayed({
                (activity as? MainActivity)?.clearBottomNavigationSelectionAndSelect(R.id.taskFragment)
                findNavController().navigate(R.id.action_taskFragment_to_addTaskFragment)
            }, 200) // Adjust the delay time as needed

        }


    }

    private fun displayAllTasks() {
        val taskList = DataManager.getTaskList()

//        taskList.forEach { task ->
//            val taskName = task.taskName ?: "Unnamed Task"
//            val duration = task.totalTaskDuration ?: 0
//            val iconName =  task.iconName ?: "Unknown Icon"
//            val toastMessage = "Task Name: $taskName, Duration: $duration Icon Name: $iconName"
//            Utils.showToast(requireContext(),toastMessage)
//        }

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = TaskListAdapter(requireContext(),taskList, navController)

    }

}
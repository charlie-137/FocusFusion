package com.brogrammer.focusfusion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.brogrammer.focusfusion.backend.DataManager
import com.brogrammer.focusfusion.databinding.FragmentAddTaskBinding
import com.brogrammer.focusfusion.dialogs.IconDialog
import com.brogrammer.focusfusion.utilities.Utils
import androidx.navigation.fragment.findNavController
import com.brogrammer.focusfusion.model.TaskModel
import com.brogrammer.focusfusion.utilities.Constants


class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding

    private val defaultIconName = Constants.allIconsNameToDrawable.keys.first() // Get the first icon name
    private val defaultIconDrawableId = Constants.allIconsNameToDrawable[defaultIconName] ?: 0 // Get the drawable id for the first icon

    var selectedIconName:String? = defaultIconName


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAddTaskBinding.inflate(layoutInflater)

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
            DataManager.addTask(taskName, duration, selectedIconName)
//            DataManager.taskList.add(TaskModel(1,"Task1",60,"Timelapse",30))
            Utils.showToast(requireContext(),"Task Added")
        } else {
            // Handle case when task name or duration is empty
            // For example, show an error message to the user
            Toast.makeText(requireContext(), "Task name or duration is empty", Toast.LENGTH_SHORT).show()
        }
    }


    private fun showIconDialog() {
        val iconDialog = IconDialog(requireContext(), object : IconDialog.OnIconSelectedListener {
            override fun onIconSelected(iconName:String, iconDrawableId: Int) {
                // Handle selected icon
//                val iconName = icons.iconName
                selectedIconName = iconName
                binding.iconImageView.setImageResource(iconDrawableId)
//                Toast.makeText(requireContext(), "Selected icon: $iconResId", Toast.LENGTH_SHORT).show()
                Utils.showToast(requireContext(),"Icon Name: $iconName")
            }
        })
        iconDialog.show()
    }

}
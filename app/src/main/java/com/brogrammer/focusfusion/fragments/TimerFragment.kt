package com.brogrammer.focusfusion.fragments

import CustomSpinnerAdapter
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.brogrammer.focusfusion.R
import com.brogrammer.focusfusion.backend.DataManager
import com.brogrammer.focusfusion.databinding.FragmentTimerBinding
import com.brogrammer.focusfusion.model.TaskModel
import com.brogrammer.focusfusion.utilities.Utils
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText

class TimerFragment : Fragment() {

//    private lateinit var progressBar: CircularProgressIndicator


//    private lateinit var startButton: Button
//    private lateinit var pauseButton: Button
//    private lateinit var resumeButton: Button
//    private lateinit var elapsedTimeTextView: TextView
    private lateinit var countDownTimer: CountDownTimer
    private var isTimerRunning = false
    private var isTimerPaused = false
    private var timeLeftInMillis: Long = 0
    private var startTimeInMillis: Long = 0
    private var tickDuration: Long = 1000 // Default tick duration in milliseconds

    private lateinit var binding: FragmentTimerBinding
    private lateinit var taskList: ArrayList<TaskModel>

    private var selectedItemPosition: Int = -1

    private var taskId:Int? = null
    private var currentTask:TaskModel? = null


    override fun onResume() {
        super.onResume()
        setUpDropDownMenu()
        setUpTimer()
        taskId = arguments?.getInt("taskId")
//        Utils.showToast(requireContext(),taskId.toString())
        if(taskId!=null)
        {
            val task = DataManager.getTaskById(taskId!!)
            currentTask = task!!
            binding.taskSpinner.setText(task?.taskName,false)
            binding.dailyGoal.text = task?.totalTaskDuration.toString()
            binding.completedGoal.text = task?.completedTaskDuration.toString()
        }

    }

    private fun setUpTimer() {

        binding.apply {

            startButton.setOnClickListener {
                startTimer()
            }

            pauseButton.setOnClickListener {
                pauseTimer()
            }

            resumeButton.setOnClickListener {
                resumeTimer()
            }

        }

    }

    private fun resumeTimer() {
        if (isTimerPaused) { // Check if the timer is paused
            countDownTimer = object : CountDownTimer(timeLeftInMillis, tickDuration) { // Resume the timer with remaining time
                override fun onTick(millisUntilFinished: Long) {
                    timeLeftInMillis = millisUntilFinished
                    updateCountDownText()
                    binding.progressBar.progress++
                }

                override fun onFinish() {
                    isTimerRunning = false
                    updateUI()
                }
            }.start()

            isTimerRunning = true
            isTimerPaused = false
            updateUI()
        }
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        isTimerRunning = false
        isTimerPaused = true // Set the pause status when pausing the timer
        updateUI()
    }

    private fun startTimer() {
//        val input = durationEditText.text.toString()
        val taskDuration = currentTask?.totalTaskDuration ?:0
        if (taskDuration > 0) {
            // Cancel the existing timer if running
//            if (::countDownTimer.isInitialized) {
//                countDownTimer.cancel()
//            }
//            val timeInSeconds = input.toLong()
//            startTimeInMillis = timeInSeconds * 1000
//            val timeInMinutes = input.toLong()
            val timeInMinutes = taskDuration
            val startTimeInMillis = timeInMinutes * 60 * 1000 // Convert minutes to milliseconds
            timeLeftInMillis = startTimeInMillis.toLong()

            val tickCount = timeInMinutes * 60 // Number of ticks equals duration in seconds
            tickDuration = (startTimeInMillis / tickCount).toLong() // Calculate tick duration

            binding.progressBar.max = tickCount.toInt()
            binding.progressBar.progress = 0 // Reset progress to 0

            countDownTimer = object : CountDownTimer(timeLeftInMillis, tickDuration) {
                override fun onTick(millisUntilFinished: Long) {
                    timeLeftInMillis = millisUntilFinished
                    updateCountDownText()
//                    updateProgressBar()
                    binding.progressBar.progress++
                }

                override fun onFinish() {
                    isTimerRunning = false
                    updateUI()
                }
            }.start()

            isTimerRunning = true
            updateUI()
        } else {
            Utils.showToast(requireContext(),"Timer Duration not set")
        }
    }

    private fun updateCountDownText() {
        val hours = (timeLeftInMillis / 1000) / 3600
        val minutes = ((timeLeftInMillis / 1000) % 3600) / 60
        val seconds = (timeLeftInMillis / 1000) % 60

        val timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        binding.elapsedTimeTextView.text = "$timeFormatted"
    }

    private fun updateUI() {
        binding.startButton.isEnabled = !isTimerRunning
        binding.pauseButton.isEnabled = isTimerRunning
//        resumeButton.isEnabled = !isTimerPaused // Enable the resume button only when the timer is paused
//        durationEditText.isEnabled = !isTimerRunning


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
            currentTask = selectedItem
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
//            val taskList = DataManager.getTaskList()
//            taskList.forEach { task ->
//                val taskName = task.taskName ?: "Unnamed Task"
//                val duration = task.totalTaskDuration ?: 0
//                val iconName = task.iconName ?: "Unknown Icon"
//                val toastMessage = "Task Name: $taskName, Duration: $duration Icon Name: $iconName"
//                Utils.showToast(requireContext(), toastMessage)
//            }
            Utils.showToast(requireContext(),currentTask?.taskName.toString()+" "+currentTask?.totalTaskDuration+" "+currentTask?.completedTaskDuration)
        }

    }



}


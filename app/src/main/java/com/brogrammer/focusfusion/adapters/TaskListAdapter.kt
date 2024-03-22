package com.brogrammer.focusfusion.adapters

import android.animation.LayoutTransition
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.brogrammer.focusfusion.R
import com.brogrammer.focusfusion.backend.DataManager
import com.brogrammer.focusfusion.databinding.SingleTaskLayoutBinding
import com.brogrammer.focusfusion.fragments.TaskFragmentDirections
import com.brogrammer.focusfusion.model.TaskModel
import com.brogrammer.focusfusion.utilities.Constants
import com.brogrammer.focusfusion.utilities.Utils
import viewmodel.TaskViewModel

class TaskListAdapter(
    private val context: Context,
    val itemList: ArrayList<TaskModel>,
    private val navController: NavController, // Inject NavController
    private val viewModel: TaskViewModel // Inject TaskViewModel
) : RecyclerView.Adapter<TaskListAdapter.MyViewHolder>() {

    // Variable to keep track of the currently playing task position
    var currentPlayingPosition: Int? = null

    // Variable to keep track of the currently expanded item position
    private var expandedPosition: Int? = null

//    inner class MyViewHolder(itemView: View)
//        :RecyclerView.ViewHolder(itemView) {
//
//            var taskName: TextView
//            var taskDuration: TextView
//            var iconImage: ImageView
//
//            init {
//                taskName = itemView.findViewById(R.id.taskTitle)
//                taskDuration = itemView.findViewById(R.id.taskTotalTime)
//                iconImage = itemView.findViewById(R.id.taskIcon)
//            }
//
//    }

    inner class MyViewHolder(val binding: SingleTaskLayoutBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {



        init {

            // Setting the toggle/expand card on the item Card View
            // Adding on click listener to the item Card View
            binding.singleTaskItem.setOnClickListener {
                val position = adapterPosition
                if (position == expandedPosition) {
                    // If this item is already expanded, collapse it
                    expandedPosition = null
                } else {
                    // Expand this item
                    expandedPosition = position
                }
                notifyDataSetChanged() // Notify adapter about the change in expansion state
            }

            // Adding on click listener to the edit button
            binding.editBtn.setOnClickListener {

                val task = itemList[adapterPosition] // Get the task at the clicked position
                val taskId = task.id // Assuming id is a unique identifier of the task
                val bundle = bundleOf("taskId" to taskId)
                navController.navigate(R.id.action_taskFragment_to_addTaskFragment,bundle)


            }

            binding.deleteBtn.setOnClickListener {
                Utils.showToast(context, "Delete Button Clicked")

                val task = itemList[adapterPosition]
                val taskId = task.id
                DataManager.deleteTask(taskId)

                notifyItemRemoved(adapterPosition)

            }

            binding.playButton.setOnClickListener {
                val clickedPosition = adapterPosition
                val task = itemList[clickedPosition]

                if (clickedPosition == currentPlayingPosition) {
                    // Toggle play/pause state for the same item
                    task.playPauseState = !task.playPauseState
                } else {
                    // Deactivate play state of the previously playing item (if any)
                    currentPlayingPosition?.let { prevPlayingPos ->
                        itemList[prevPlayingPos].playPauseState = false
                        notifyItemChanged(prevPlayingPos)
                    }
                    // Activate play state of the clicked item
                    task.playPauseState = true
                    currentPlayingPosition = clickedPosition
                }

                // Update UI based on play/pause state
                updatePlayButtonState(binding.playButton, task.playPauseState)

                // Update the currentPlayingPosition in the ViewModel
                viewModel.setCurrentPlayingPosition(currentPlayingPosition)

//                if(!task.playPauseState)
//                {
//                    binding.playButton.setImageResource(R.drawable.baseline_pause_circle_outline_24)
//                    task.playPauseState=true
//                }else{
//                    binding.playButton.setImageResource(R.drawable.baseline_play_circle_outline_24)
//                    task.playPauseState=false
//                }

            }

        }

        private fun toggleExpand(view: View) {
//             binding.taskLayout.layoutTransition = LayoutTransition()
//             binding.taskLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            val visibility = if (view.visibility == View.GONE) View.VISIBLE else View.GONE
            view.visibility = visibility
        }


    }

    private fun updatePlayButtonState(playButton: ImageView, isPlaying: Boolean) {
        val drawableRes = if (isPlaying) {
            R.drawable.baseline_pause_circle_outline_24
        } else {
            R.drawable.baseline_play_circle_outline_24
        }
        playButton.setImageResource(drawableRes)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

//        val v = LayoutInflater.from(parent.context)
//            .inflate(R.layout.single_task_layout,parent,false)
//
//        return MyViewHolder(v)

        return MyViewHolder(
            SingleTaskLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),context
        )

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val task = itemList[position]
        val iconImage = Constants.getIconDrawableRes(task.iconName!!)

//        holder.taskName.text = itemList[position].taskName
//        holder.taskDuration.text = itemList[position].totalTaskDuration.toString()
//        holder.iconImage.setImageResource(iconImage!!)


//        val name = task.taskName
//        val iconName = task.iconName
//        val time = task.totalTaskDuration
//
//        Log.d("TaskListTag", "Name: $name IconName: $iconName Time: $time")

        holder.binding.apply {
            taskTitle.text = task.taskName
            taskTotalTime.text = task.totalTaskDuration.toString()
            taskIcon.setImageResource(iconImage!!)

        }

        // Update play button state based on the play/pause state of the task
        updatePlayButtonState(holder.binding.playButton, task.playPauseState)

        // Set visibility of expandable content based on expansion state
        holder.binding.expandCardView.visibility = if (position == expandedPosition) View.VISIBLE else View.GONE

    }
}
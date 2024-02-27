package com.brogrammer.focusfusion.adapters

import android.animation.LayoutTransition
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brogrammer.focusfusion.R
import com.brogrammer.focusfusion.databinding.SingleTaskLayoutBinding
import com.brogrammer.focusfusion.model.TaskModel
import com.brogrammer.focusfusion.utilities.Constants

class TaskListAdapter(val itemList: ArrayList<TaskModel>)
    : RecyclerView.Adapter<TaskListAdapter.MyViewHolder>() {

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

     class MyViewHolder(val binding: SingleTaskLayoutBinding)
        :RecyclerView.ViewHolder(binding.root) {

            init {
                binding.singleTaskItem.setOnClickListener {
                    toggleExpand(binding.expandCardView)
                }
            }

         private fun toggleExpand(view: View){
//             binding.taskLayout.layoutTransition = LayoutTransition()
//             binding.taskLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
             val visibility = if (view.visibility == View.GONE) View.VISIBLE else View.GONE
             view.visibility = visibility
         }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

//        val v = LayoutInflater.from(parent.context)
//            .inflate(R.layout.single_task_layout,parent,false)
//
//        return MyViewHolder(v)

        return MyViewHolder(SingleTaskLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false))

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

    }
}
package com.brogrammer.focusfusion.fragments

import android.animation.LayoutTransition
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brogrammer.focusfusion.R
import com.brogrammer.focusfusion.databinding.FragmentSettingsBinding
import com.brogrammer.focusfusion.utilities.Utils

class SettingsFragment : Fragment() {


    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSettingsBinding.inflate(layoutInflater)

//        binding.taskLayout.layoutTransition = LayoutTransition()
//
//        binding.taskLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
//        binding.singleTaskItem.setOnClickListener {
//            val v = if (binding.expandCardView.visibility == View.GONE) {
//                View.VISIBLE
//            } else {
//                View.GONE
//            }
//            binding.expandCardView.visibility = v
//        }
//
//        binding.playButton.setOnClickListener {
//            Utils.showToast(requireContext(),"Play Button Clicked")
//        }
//
//        binding.editBtn.setOnClickListener {
//            Utils.showToast(requireContext(),"Edit Button Clicked")
//        }
//
//        binding.deleteBtn.setOnClickListener {
//            Utils.showToast(requireContext(),"Delete Button Clicked")
//        }


        return binding.root
    }

}
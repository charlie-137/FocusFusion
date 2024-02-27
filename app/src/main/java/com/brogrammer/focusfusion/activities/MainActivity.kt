package com.brogrammer.focusfusion.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.brogrammer.focusfusion.R
import com.brogrammer.focusfusion.databinding.ActivityMainBinding
import com.brogrammer.focusfusion.fragments.AddTaskFragment
import com.brogrammer.focusfusion.fragments.SettingsFragment
import com.brogrammer.focusfusion.fragments.TaskFragment
import com.brogrammer.focusfusion.fragments.TimerFragment
import com.brogrammer.focusfusion.utilities.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navController = findNavController(R.id.navHostFragment)
//        NavigationUI.setupWithNavController(binding.BottomNavigationView, navController)
        binding.BottomNavigationView.setupWithNavController(navController)

        // Set color for active state of icons
//        val activeColor = ContextCompat.getColorStateList(this, R.color.yellow)
//        binding.BottomNavigationView.itemIconTintList = activeColor

        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Update selected item in BottomNavigationView based on the current destination
            when(destination.id) {
                R.id.timerFragment -> {
                    selectBottomNavigationItem(R.id.timerFragment)
                    showBottomNavigationView()
                }
                R.id.taskFragment -> {
                    selectBottomNavigationItem(R.id.taskFragment)
                    showBottomNavigationView()
                }
                R.id.settingsFragment -> {
                    selectBottomNavigationItem(R.id.settingsFragment)
                    showBottomNavigationView()
                }
                R.id.addTaskFragment -> {
                    hideBottomNavigationView()
                }
            }
        }

    }


    private fun selectBottomNavigationItem(itemId: Int) {
        // Deselect all items first
        binding.BottomNavigationView.menu.setGroupCheckable(0, true, false)
        (0 until binding.BottomNavigationView.menu.size()).forEach { i ->
            binding.BottomNavigationView.menu.getItem(i).isChecked = false
        }

        // Select the specified item
        binding.BottomNavigationView.menu.findItem(itemId)?.isChecked = true
    }



    fun clearBottomNavigationSelectionAndSelect(itemId: Int) {

        // Deselect all items first
        binding.BottomNavigationView.menu.setGroupCheckable(0, true, false)
        (0 until binding.BottomNavigationView.menu.size()).forEach { i ->
            binding.BottomNavigationView.menu.getItem(i).isChecked = false
        }

        // Select the specified item
        binding.BottomNavigationView.menu.findItem(itemId)?.isChecked = false
    }

    private fun showBottomNavigationView() {
        binding.BottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNavigationView() {
        binding.BottomNavigationView.visibility = View.GONE
    }

}


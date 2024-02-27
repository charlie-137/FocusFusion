package com.brogrammer.focusfusion.utilities

import com.brogrammer.focusfusion.R

object Constants {

        val allIconsNameToDrawable = mapOf(
            "Timelapse" to R.drawable.baseline_timelapse_24,
            "Fitness" to R.drawable.baseline_fitness_center_24,
            "Computer" to R.drawable.baseline_computer_24,
            "Cycling" to R.drawable.baseline_directions_bike_24,
            "Yoga" to R.drawable.baseline_self_improvement_24,
            "ReadingBooks" to R.drawable.baseline_menu_book_24,
            "Coding" to R.drawable.baseline_code_24,
            "Running" to R.drawable.baseline_directions_run_24,
            "Painting" to R.drawable.baseline_brush_24,
            "Smartphone" to R.drawable.baseline_smartphone_24,
            "Listening" to R.drawable.baseline_headphones_24,
            "Todo" to R.drawable.baseline_event_note_24,
            "Gaming" to R.drawable.baseline_videogame_asset_24,
            "Star" to R.drawable.baseline_star_24,
            "Music" to R.drawable.baseline_music_note_24,
            "Newspaper" to R.drawable.baseline_newspaper_24
        )


    fun getIconDrawableRes(iconName: String): Int? {
        return allIconsNameToDrawable[iconName]
    }



}
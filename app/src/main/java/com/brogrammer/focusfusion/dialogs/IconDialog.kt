package com.brogrammer.focusfusion.dialogs

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.GridView
import com.brogrammer.focusfusion.R
import com.brogrammer.focusfusion.adapters.CustomIconAdapter
import com.brogrammer.focusfusion.databinding.IconsDialogBinding
import com.brogrammer.focusfusion.utilities.Constants

class IconDialog(context: Context, private val listener: OnIconSelectedListener) : Dialog(context) {

    private lateinit var binding: IconsDialogBinding

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = IconsDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridView: GridView = findViewById(R.id.gridView)

        val customIconAdapter = CustomIconAdapter(context, Constants.allIconsNameToDrawable, object : CustomIconAdapter.OnIconClickListener {
            override fun onIconClicked(iconDrawableId: Int, iconName: String) {
                // Pass the selected icon back to listener
                listener.onIconSelected(iconName, iconDrawableId)
                dismiss()
            }
        })
        gridView.adapter = customIconAdapter

        binding.closeButton.setOnClickListener {
            dismiss()
        }
    }

    interface OnIconSelectedListener {
        fun onIconSelected(iconName: String, iconDrawable: Int)
    }
}

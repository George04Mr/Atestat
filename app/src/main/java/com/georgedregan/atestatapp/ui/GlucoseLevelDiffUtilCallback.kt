package com.georgedregan.atestatapp.ui

import androidx.recyclerview.widget.DiffUtil
import com.georgedregan.atestatapp.data.GlucoseLevel

class GlucoseLevelDiffUtilCallback : DiffUtil.ItemCallback<GlucoseLevel>() {

    override fun areItemsTheSame(oldItem: GlucoseLevel, newItem: GlucoseLevel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: GlucoseLevel, newItem: GlucoseLevel): Boolean =
        oldItem == newItem
}
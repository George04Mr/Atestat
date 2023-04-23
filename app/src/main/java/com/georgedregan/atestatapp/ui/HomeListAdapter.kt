package com.georgedregan.atestatapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.georgedregan.atestatapp.R
import com.georgedregan.atestatapp.data.GlucoseLevel

class HomeListAdapter :
    ListAdapter<GlucoseLevel, GlucoseItemViewHolder>(GlucoseLevelDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlucoseItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_glucose_level, parent, false)
        return GlucoseItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: GlucoseItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
package com.georgedregan.atestatapp.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.georgedregan.atestatapp.R
import com.georgedregan.atestatapp.data.GlucoseLevel

class GlucoseItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(glucoseLevel: GlucoseLevel) {
        val dateTV = itemView.findViewById<TextView>(R.id.dateTV)
        val levelTV = itemView.findViewById<TextView>(R.id.levelTV)

        levelTV.text = "Nivel glicemie: ${glucoseLevel.level}"
    }
}
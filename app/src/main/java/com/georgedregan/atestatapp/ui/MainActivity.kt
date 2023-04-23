package com.georgedregan.atestatapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.georgedregan.atestatapp.R
import com.georgedregan.atestatapp.data.GlucoseLevel

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { HomeListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contentRV = findViewById<RecyclerView>(R.id.contentRV)
        contentRV.adapter = adapter

        // ToDo Retrieve data from db
        adapter.submitList(
            listOf(
                GlucoseLevel(123L, 85),
                GlucoseLevel(123L, 90),
                GlucoseLevel(123L, 160)
            )
        )
    }
}
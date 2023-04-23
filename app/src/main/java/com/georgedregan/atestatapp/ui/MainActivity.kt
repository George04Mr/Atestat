package com.georgedregan.atestatapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.georgedregan.atestatapp.R
import com.georgedregan.atestatapp.data.GlucoseLevel
import com.georgedregan.atestatapp.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { HomeListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contentRV = findViewById<RecyclerView>(R.id.contentRV)
        contentRV.adapter = adapter

        // Launch on Background Thread
        CoroutineScope(IO).launch {
            // We take db instance
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            val glucoseLevelDao = db.glucoseLevelDao()
            val glucoseLevel = GlucoseLevel(123L, "George", 85)
            glucoseLevelDao.insert(glucoseLevel)

            // ToDo Retrieve data from db
            val glucoseLevelList = glucoseLevelDao.getAll()
            adapter.submitList(glucoseLevelList)
        }
    }
}
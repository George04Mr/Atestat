package com.georgedregan.atestatapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.georgedregan.atestatapp.data.GlucoseLevel

@Database(entities = [GlucoseLevel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun glucoseLevelDao(): GlucoseLevelDao
}

package com.georgedregan.atestatapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.georgedregan.atestatapp.data.GlucoseLevel
import com.georgedregan.atestatapp.data.User

@Database(entities = [GlucoseLevel::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun glucoseLevelDao(): GlucoseLevelDao

    abstract fun userDao(): UserDao
}

package com.georgedregan.atestatapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.georgedregan.atestatapp.data.GlucoseLevel

@Dao
interface GlucoseLevelDao {
    @Query("SELECT * FROM glucoseLevel WHERE user_name LIKE :username")
    fun getAll(username: String): List<GlucoseLevel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(glucoseLevel: GlucoseLevel)

    @Query("DELETE FROM glucoseLevel")
    fun deleteAll()
}
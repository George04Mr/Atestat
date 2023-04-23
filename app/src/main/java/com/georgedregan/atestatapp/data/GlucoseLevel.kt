package com.georgedregan.atestatapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GlucoseLevel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "level") val level: Int
)
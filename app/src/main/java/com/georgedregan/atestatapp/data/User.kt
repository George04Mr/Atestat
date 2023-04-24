package com.georgedregan.atestatapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_name") val username: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "is_logged_in") val isLoggedIn: Boolean
)
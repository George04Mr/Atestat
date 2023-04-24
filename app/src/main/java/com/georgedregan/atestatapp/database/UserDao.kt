package com.georgedregan.atestatapp.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.georgedregan.atestatapp.data.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE user_name LIKE :username")
    fun getUser(username: String): User?

    @Query("SELECT * FROM user WHERE is_logged_in == 1")
    fun getLoggedInUser(): User?

    @Upsert
    fun insert(user: User)

    @Query("DELETE FROM user")
    fun deleteAll()
}
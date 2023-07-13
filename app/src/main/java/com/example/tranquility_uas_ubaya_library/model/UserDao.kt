package com.example.tranquility_uas_ubaya_library.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

//    @Query("SELECT * FROM user WHERE username=:username")
//    suspend fun getUserById(username: String): User?

    @Query("SELECT * FROM user")
    suspend fun getAllUser(): User?

    @Query("SELECT * FROM user WHERE username = :username AND " + "password = :password")
//    fun getUserByUsernameLiveData(username: String, password: String): LiveData<User?>
    fun getUserByUsernamePassword(username: String, password: String):User
}
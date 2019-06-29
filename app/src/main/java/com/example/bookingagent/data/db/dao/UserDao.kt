package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookingagent.data.db.entities.UserEntity
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(userEntity: UserEntity): Long

    @Query("SELECT token from localUsers where username = :username and password = :password")
    fun getUserToken(username: String, password: String): Single<String>

    @Query("SELECT * from localUsers where token = :token")
    fun getUserByToken(token: String) : Single<UserEntity>


}
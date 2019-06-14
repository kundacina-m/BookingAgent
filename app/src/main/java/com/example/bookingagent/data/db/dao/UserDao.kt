package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookingagent.data.db.entities.User
import io.reactivex.Single

@Dao
interface UserDao {

	@Insert(onConflict = OnConflictStrategy.FAIL)
	fun addUser(user: User) : Long

	@Delete
	fun deleteUser(user: User)

	@Query("SELECT * from localUsers where username = :username")
	fun getUser(username: String): Single<User>
}
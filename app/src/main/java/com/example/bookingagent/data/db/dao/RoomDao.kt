package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookingagent.data.db.entities.Room
import io.reactivex.Single

@Dao
interface RoomDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addRoom(room: Room): Long

	@Query("SELECT * from Room where id == :id")
	fun getRoomById(id: Int): Single<Room>

	@Query("DELETE from Room where id == :id")
	fun deleteRoom(id: Int): Int

	@Update
	fun updateRoom(room: Room): Int
}
package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookingagent.data.db.entities.RoomEntity
import io.reactivex.Single

@Dao
interface RoomDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addRoom(roomEntity: RoomEntity): Long

	@Query("SELECT * from RoomEntity where id == :id")
	fun getRoomById(id: Int): Single<RoomEntity>

	@Query("DELETE from RoomEntity where id == :id")
	fun deleteRoom(id: Int): Int

	@Update
	fun updateRoom(roomEntity: RoomEntity): Int
}
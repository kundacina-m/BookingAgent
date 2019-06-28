package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookingagent.data.db.entities.MessageEntity

@Dao
interface MessageDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addMessage(message: MessageEntity): Long

	@Query("DELETE from MessageEntity where id = :id")
	fun deleteMessage(id: Int): Int

}
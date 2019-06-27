package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.bookingagent.data.db.entities.MessageEntity
import io.reactivex.Flowable

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMessage(message: MessageEntity): Long

}
package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.bookingagent.data.db.entities.ResMessEntity

@Dao
interface ResMessDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addResMess(resMess: ResMessEntity) : Long

}
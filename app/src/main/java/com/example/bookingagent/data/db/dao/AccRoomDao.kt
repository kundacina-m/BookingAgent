package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookingagent.data.db.entities.AccRoom
import com.example.bookingagent.data.db.entities.Room
import io.reactivex.Single

@Dao
interface AccRoomDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addAccRoomRow(accRoom: AccRoom) : Long

	@Query("SELECT roomId from AccRoom where accId == :accId")
	fun getRoomIdsByAccId(accId: Int): Single<List<Int>>

	@Query("""
		 SELECT * from Room room 
		 left join AccRoom accRoom on room.id = accRoom.roomId 
		 where accRoom.accId == :accId
		""")
	fun getRoomsByAccId(accId: Int): Single<List<Room>>

}
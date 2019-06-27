package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookingagent.data.db.entities.MessageEntity
import com.example.bookingagent.data.db.entities.ResMessEntity
import com.example.bookingagent.data.db.entities.ReservationEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ResMessDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addResMess(resMess: ResMessEntity) : Long

    @Query("""
		 SELECT * from ReservationEntity reservationEntity 
		 left join ResMessEntity resMess on reservationEntity.id = resMess.resId 
		 group by resId
		""")
    fun getReservationsThatHaveMessages() : Single<List<ReservationEntity>>

    @Query("""
        SELECT * FROM MessageEntity messageEntity
         left join ResMessEntity resMess on messageEntity.id = resMess.messId
         where resId = :resId order by messId
    """)
    fun getMessageThreadByResId(resId: Int) : Flowable<List<MessageEntity>>

}
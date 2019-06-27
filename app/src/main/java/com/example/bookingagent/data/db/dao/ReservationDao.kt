package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookingagent.data.db.entities.ReservationEntity
import io.reactivex.Flowable

@Dao
interface ReservationDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addReservation(reservationEntity: ReservationEntity): Long

	@Query("SELECT * from ReservationEntity")
	fun getAllReservations(): Flowable<List<ReservationEntity>>

	@Query("UPDATE ReservationEntity SET reservationUsed =:used where id = :id")
	fun updateUsedInReservation(id: Int, used: Boolean)
}
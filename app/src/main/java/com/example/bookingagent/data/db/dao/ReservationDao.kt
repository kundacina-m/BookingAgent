package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bookingagent.data.db.entities.ReservationEntity
import io.reactivex.Flowable

@Dao
interface ReservationDao {

	@Insert
	fun addReservation(reservationEntity: ReservationEntity): Long

	@Query("SELECT * from ReservationEntity")
	fun getAllReservations(): Flowable<List<ReservationEntity>>
}
package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookingagent.data.db.entities.AccommodationEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface AccommodationDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addAccommodation(accommodationEntity: AccommodationEntity): Long

	@Query("SELECT * from AccommodationEntity")
	fun getAllAccommodation(): Flowable<List<AccommodationEntity>>

	@Query("DELETE from AccommodationEntity where id == :id")
	fun deleteAccommodation(id: Int): Int

	@Update
	fun updateAccommodation(accommodationEntity: AccommodationEntity): Int

	@Query("SELECT * from AccommodationEntity where id == :id")
	fun getAccommodation(id: Int): Single<AccommodationEntity>
}
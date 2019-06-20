package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookingagent.data.db.entities.Accommodation
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface AccommodationDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addAccommodation(accommodation: Accommodation): Long

	@Query("SELECT * from Accommodation")
	fun getAllAccommodation(): Flowable<List<Accommodation>>

	@Query("DELETE from Accommodation where id == :id")
	fun deleteAccommodation(id: Int): Int

	@Update
	fun updateAccommodation(accommodation: Accommodation): Int

	@Query("SELECT * from Accommodation where id == :id")
	fun getAccommodation(id: Int): Single<Accommodation>
}
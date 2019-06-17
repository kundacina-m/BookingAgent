package com.example.bookingagent.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.db.entities.Address

@Dao
interface AccommodationDao {

	@Insert(onConflict = OnConflictStrategy.FAIL)
	fun addAccommodation(accommodation: Accommodation): Long

	@Insert(onConflict = OnConflictStrategy.FAIL)
	fun addAddress(address: Address): Long

}
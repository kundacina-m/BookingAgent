package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReservationEntity(
	@PrimaryKey
	val id: Int,
	val firstName: String? = "",
	val lastName: String? = "",
	val email: String? = "",
	val room: RoomEntity? = null,
	val from: String? = "",
	val to: String? = "",
	val price: Float? = 0f
)
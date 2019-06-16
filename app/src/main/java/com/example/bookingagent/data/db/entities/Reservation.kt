package com.example.bookingagent.data.db.entities

import androidx.room.PrimaryKey
import java.sql.Time

data class Reservation(
	@PrimaryKey
	val roomId: Int,
	val userId: Int,
	val from: Time,
	val to: Time
)
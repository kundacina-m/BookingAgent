package com.example.bookingagent.data.db.entities

import java.sql.Time

data class Reservation(
	val accommodationId: Int,
	val from: Time,
	val to: Time
)
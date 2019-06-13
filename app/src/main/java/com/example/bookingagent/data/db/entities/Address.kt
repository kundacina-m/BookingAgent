package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class Address(

	@PrimaryKey
	val id: Int,
	val latitude: Int,
	val longitude: Int,
	val city: String,
	val zipCode: Int,
	val street: String,
	val num: Int
)
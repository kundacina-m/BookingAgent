package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "service")
data class Service(

	@PrimaryKey
	val id: Int,
	val name: String,
	val description: String,
	val price: Int
)
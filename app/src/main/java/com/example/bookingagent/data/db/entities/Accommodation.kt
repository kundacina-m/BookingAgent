package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accommodation")
data class Accommodation(

	@PrimaryKey
	val id: Int,
	val name: String,
	val description: String,
//	val address: Address,
	val type: String,
	val beds: Int,
	val cancelingFee: Float,
	val rating: Float
//	val services: List<Service>,
//	val images: List<String>,
//	val comments: List<String>
)
package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accommodation")
data class Accommodation(

	@PrimaryKey
	val id: Int,
	val name: String? = "",
	val description: String? = "",
	val address: Address,
	val type: String? = "",
	val cancelingFee: Float? = 0f,
	val rating: Float? = 0f,
	val category: String? = "",
	val services: ArrayList<Service> = arrayListOf(),
	val rooms: ArrayList<Room> = arrayListOf()
)
package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookingagent.data.model.Address
import com.example.bookingagent.data.model.Service

@Entity
data class AccommodationEntity(

	@PrimaryKey
	var id: Int,
	var name: String? = "",
	var description: String? = "",
	var address: Address,
	var type: String? = "",
	var cancellingFee: Float? = 0f,
	var cancellingDays: Int? = 0,
	var rating: Float? = 0f,
	var category: String? = "",
	var services: ArrayList<Service> = arrayListOf(),
	var pictures: ArrayList<String> = arrayListOf()
)

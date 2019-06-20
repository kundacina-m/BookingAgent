package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookingagent.data.model.Address
import com.example.bookingagent.data.model.Service

@Entity
data class Accommodation(

	@PrimaryKey
	var id: Int,
	var name: String? = "",
	var description: String? = "",
	var address: Address,
	var type: String? = "",
	var cancellingFee: Float? = 0f,
	var rating: Float? = 0f,
	var category: String? = "",
	var services: ArrayList<Service> = arrayListOf()
)

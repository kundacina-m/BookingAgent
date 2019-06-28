package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookingagent.data.model.Address

@Entity(tableName = "localUsers")
data class UserEntity(

	@PrimaryKey
	var id: Int = 0,
	val username: String? = null,
	val password: String? = null,
	var firstname: String? = null,
	var lastname: String? = null,
	var address: Address? = null,
	var email: String? = null

)
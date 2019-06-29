package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookingagent.data.model.Address

@Entity(tableName = "localUsers")
data class UserEntity(

	@PrimaryKey
	var id: Int = 0,
	val username: String = "",
	val password: String = "",
	var firstname: String = "",
	var lastname: String = "",
	var address: Address,
	var email: String = "",
	var token: String = ""
)
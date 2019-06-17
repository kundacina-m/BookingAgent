package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localUsers")
data class User(

	@PrimaryKey
	val username: String,
	val password: String,
	val linkedAccount: String? = null

)
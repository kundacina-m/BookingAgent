package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "localUsers")
data class LocalUserEntity(

	@PrimaryKey
	val username: String,

	val password: String,

	val linkedAccount: String? = null

)
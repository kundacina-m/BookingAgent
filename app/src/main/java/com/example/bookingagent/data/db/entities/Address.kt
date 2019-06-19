package com.example.bookingagent.data.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "address")
@Parcelize
data class Address(

	@PrimaryKey
	val id: Int? = 0,
	val latitude: Float? = 0f,
	val longitude: Float? = 0f,
	val city: String? = "",
	val zipCode: Int? = 0,
	val street: String? = "",
	val num: Int? = 0
) : Parcelable
package com.example.bookingagent.data.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "address")
@Parcelize
data class Address(

	@PrimaryKey
	val id: Int,
	val latitude: Int,
	val longitude: Int,
	val city: String,
	val zipCode: Int,
	val street: String,
	val num: Int
) : Parcelable
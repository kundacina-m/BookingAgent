package com.example.bookingagent.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(

	@PrimaryKey
	var id: Int? = 0,
	val latitude: Float? = 0f,
	val longitude: Float? = 0f,
	val city: String? = "",
	val zipCode: Int? = 0,
	val street: String? = "",
	val num: Int? = 0
) : Parcelable
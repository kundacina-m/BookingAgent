package com.example.bookingagent.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Service(

	@PrimaryKey
	val id: Int,
	val name: String,
	val description: String,
	val price: Float
) : Parcelable
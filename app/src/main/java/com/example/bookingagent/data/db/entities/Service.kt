package com.example.bookingagent.data.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "service")
@Parcelize
data class Service(

	@PrimaryKey
	val id: Int,
	val name: String,
	val description: String,
	val price: Float
) : Parcelable
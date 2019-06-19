package com.example.bookingagent.data.db.entities

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Room(

	@PrimaryKey
	val id: Int,
	val roomNum: Int? = null,
	val floor: Int? = null,
	val bedNums: Int? = null,
	val price: Float? = null,
	val availability: Boolean? = null,
	val comments: ArrayList<String>? = null,
	val images: ArrayList<String>? = null,
	val schedule: ArrayList<ScheduleUnit>? = null
) : Parcelable
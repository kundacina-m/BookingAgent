package com.example.bookingagent.data.db.entities

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.GregorianCalendar

@Parcelize
data class ScheduleUnit(
	@PrimaryKey
	val id: Int,
	val checkIn: GregorianCalendar,
	val checkOut: GregorianCalendar,
	val price: Float
) : Parcelable
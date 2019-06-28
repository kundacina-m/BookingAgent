package com.example.bookingagent.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.GregorianCalendar

@Parcelize
data class ScheduleUnit(
	val checkIn: GregorianCalendar,
	val checkOut: GregorianCalendar,
	val price: Float
) : Parcelable
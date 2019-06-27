package com.example.bookingagent.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.GregorianCalendar

@Parcelize
data class OccupiedTime(
	val startDate: GregorianCalendar,
	val endDate: GregorianCalendar
) : Parcelable
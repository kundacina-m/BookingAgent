package com.example.bookingagent.data.db.entities

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
	@PrimaryKey
	val id: Int,
	val src: String
) : Parcelable
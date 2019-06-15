package com.example.bookingagent.data.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "accommodation")
@Parcelize
data class Accommodation(

	@PrimaryKey
	val id: Int,
	val name: String,
	val description: String,
	val address: Int,
	val type: String,
	val beds: Int,
	val cancelingFee: Long,
	val images: ArrayList<String>
	//	val services: ArrayList<Service>
	//	val comments: ArrayList<Comment>
) : Parcelable
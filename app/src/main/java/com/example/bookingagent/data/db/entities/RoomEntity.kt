package com.example.bookingagent.data.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookingagent.data.model.OccupiedTime
import com.example.bookingagent.data.model.ScheduleUnit
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class RoomEntity(

	@PrimaryKey
	val id: Int,
	val roomNum: Int = 0,
	val floor: Int = 0,
	val bedNums: Int = 0,
	val price: Float = 0f,
	val occupied: ArrayList<OccupiedTime> = arrayListOf(),
	val comments: ArrayList<String> = arrayListOf(),
	val images: ArrayList<String> = arrayListOf(),
	val schedule: ArrayList<ScheduleUnit> = arrayListOf()
) : Parcelable
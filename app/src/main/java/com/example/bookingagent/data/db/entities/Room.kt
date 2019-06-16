package com.example.bookingagent.data.db.entities

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Room(

    @PrimaryKey
    val id: Long,
    val roomNum: Int,
    val floor: Int,
    val bedNums: Int,
    val price: Int,
    val availability: Boolean,
    val comments: ArrayList<String>,
    val images: ArrayList<String>
) : Parcelable
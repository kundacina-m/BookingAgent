package com.example.bookingagent.data.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class MessageEntity(

	@PrimaryKey
	val id: Int,
	val firstname: String,
	val lastname: String,
	val content: String,
	val agentEmail: String,
	val sender: String
) : Parcelable
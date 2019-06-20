package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(
	primaryKeys = ["accId", "roomId"],
	foreignKeys = [
		ForeignKey(
			entity = Accommodation::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("accId"),
			onDelete = CASCADE
		),
		ForeignKey(
			entity = Room::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("roomId"),
			onDelete = CASCADE
		)]
)
data class AccRoom(
	val accId: Int,
	val roomId: Int
)
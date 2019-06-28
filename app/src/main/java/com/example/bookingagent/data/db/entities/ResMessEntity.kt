package com.example.bookingagent.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
	primaryKeys = ["resId", "messId"],
	foreignKeys = [
		ForeignKey(
			entity = ReservationEntity::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("resId"),
			onDelete = ForeignKey.CASCADE
		),
		ForeignKey(
			entity = MessageEntity::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("messId"),
			onDelete = ForeignKey.CASCADE
		)]
)
data class ResMessEntity(
	val resId: Int,
	val messId: Int
)
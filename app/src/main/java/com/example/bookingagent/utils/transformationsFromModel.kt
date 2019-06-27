package com.example.bookingagent.utils

import com.example.bookingagent.data.db.entities.AccommodationEntity
import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.data.networking.accommodation.models.AddChangeAccommodationRequest
import com.example.bookingagent.data.networking.room.models.AddChangeRoomRequest

fun RoomEntity.toRequest(accId: Int = this.id) =
	AddChangeRoomRequest(
		accId = accId,
		roomNum = roomNum!!,
		floor = floor!!,
		price = price!!,
		images = images,
		bedsNum = bedNums!!,
		available = true,
		timePrice = arrayListOf()
	)

fun AccommodationEntity.toRequest() =
	AddChangeAccommodationRequest(
		name = name,
		description = description,
		id = id,
		cancellingFee = cancellingFee,
		type = type,
		num = address.num,
		zipCode = address.zipCode,
		street = address.street,
		city = address.city,
		longitude = address.longitude,
		latitude = address.latitude,
		services = services.run {
			val listOfServices = arrayListOf<String>()
			forEach {
				val service = "" + it.name + "~" + it.description + "~" + it.price.toString()
				listOfServices.add(service)
			}
			listOfServices

		},
		pictures = pictures
	)
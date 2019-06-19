package com.example.bookingagent.utils

import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.networking.accommodation.models.AddChangeAccommodationRequest
import com.example.bookingagent.data.networking.room.models.AddChangeRoomRequest

fun Room.toRequest(accId: Int = this.id.toInt()) =
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

fun Accommodation.toRequest() =
	AddChangeAccommodationRequest(
		name = name,
		description = description,
		id = id,
		cancellingFee = cancelingFee,
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

		}
	)
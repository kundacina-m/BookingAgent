package com.example.bookingagent.utils

import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.db.entities.Address
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.db.entities.ScheduleUnit
import com.example.bookingagent.data.db.entities.Service
import com.example.bookingagent.data.networking.accommodation.models.AddChangeAccommodationRequest
import com.example.bookingagent.data.networking.common.FullAccommodation
import java.util.GregorianCalendar

fun AddChangeAccommodationRequest.toModel(idAcc: Int, idAddress: Int, rating: Float = 0f, category: String = "") =
	Accommodation(
		id = idAcc,
		name = name,
		description = description,
		address = Address(
			id = idAddress,
			num = num,
			zipCode = zipCode,
			street = street,
			city = city,
			longitude = longitude,
			latitude = latitude
		),
		type = type,
		rating = rating,
		cancelingFee = cancellingFee,
		category = category,
		rooms = arrayListOf(),
		services = arrayListOf()
	)

fun FullAccommodation.toModel() =
	Accommodation(
		id = id,
		name = name,
		description = desc,
		address = Address(
			id = address?.id,
			num = address?.num,
			zipCode = address?.zipCode,
			street = address?.street,
			city = address?.city,
			longitude = address?.longitude,
			latitude = address?.latitude
		),
		type = type,
		rating = if (rating == null) 0f else rating,
		cancelingFee = cancellingFee,
		category = if (category == null) "" else category,
		rooms = rooms.run {
			val listOfRooms = arrayListOf<Room>()
			this?.forEach {
				listOfRooms.add(Room(
					id = it.id.toLong(),
					price = it.price,
					floor = it.floor,
					roomNum = it.roomNum,
					bedNums = it.bedsNum,
					availability = it.isAvaiability,
					images = it.images,
					comments = it.comments,
					schedule = it.timePrice.run {
						val listOfSchedule = arrayListOf<ScheduleUnit>()
						this?.forEach { schedule ->
							schedule.split("~").also { splitted ->
								listOfSchedule.add(
									ScheduleUnit(GregorianCalendar(), GregorianCalendar(), splitted[0].toFloat()))
							}
						}
						listOfSchedule
					}
				))
			}
			listOfRooms
		},
		services = this.services.run {
			val listOfServices = arrayListOf<Service>()
			this?.forEach {
				it.split("~").also { splitted ->
					listOfServices.add(Service(1, splitted[0], splitted[1], splitted[2].toFloat()))
				}
			}
			listOfServices
		}
	)



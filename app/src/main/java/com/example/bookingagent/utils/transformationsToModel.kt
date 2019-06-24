package com.example.bookingagent.utils

import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.model.Address
import com.example.bookingagent.data.model.OccupiedTime
import com.example.bookingagent.data.model.ScheduleUnit
import com.example.bookingagent.data.model.Service
import com.example.bookingagent.data.networking.accommodation.models.AddChangeAccommodationRequest
import com.example.bookingagent.data.networking.utilresponse.AccommodationResponse
import com.example.bookingagent.data.networking.utilresponse.RoomResponse
import java.util.GregorianCalendar

fun AddChangeAccommodationRequest.toAccommodationModel(idAcc: Int, idAddress: Int, rating: Float = 0f,
	category: String = "") =
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
		cancellingFee = cancellingFee,
		category = category,
		services = arrayListOf()
	)

fun AccommodationResponse.toAccommodationModel() =
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
		cancellingFee = cancellingFee.toFloat(),
		category = if (category == null) "" else category,
		services = this.services.run {
			val listOfServices = arrayListOf<Service>()
			this?.forEach {
				it.split("~").also { splitted ->
					listOfServices.add(
						Service(1, splitted[0], splitted[1], splitted[2].toFloat()))
				}
			}
			listOfServices
		}
	)

fun RoomResponse.toRoomsModel() =
	Room(
		id = id,
		price = price,
		floor = floor,
		roomNum = roomNum,
		bedNums = bedsNum,
		occupied = occupied.run {
			val listOfOccupation = arrayListOf<OccupiedTime>()
			this?.forEach { time ->
				time.split("~").also { splitted ->
					listOfOccupation.add(
						OccupiedTime(GregorianCalendar(), GregorianCalendar()))
				}
			}
			listOfOccupation
		},
		images = images,
		comments = comments,
		schedule = timePrice.run {
			val listOfSchedule = arrayListOf<ScheduleUnit>()
			this?.forEach { schedule ->
				schedule.split("~").also { splitted ->
					listOfSchedule.add(
						ScheduleUnit(GregorianCalendar(), GregorianCalendar(),
							splitted[0].toFloat()))
				}
			}
			listOfSchedule
		}
	)

fun Accommodation.addId(accId: Int) =
	Accommodation(accId, name, description, address, type, cancellingFee, rating, category, services)






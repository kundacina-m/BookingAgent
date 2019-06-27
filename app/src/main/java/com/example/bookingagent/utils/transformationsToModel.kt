package com.example.bookingagent.utils

import com.example.bookingagent.data.db.entities.*
import com.example.bookingagent.data.model.*
import com.example.bookingagent.data.networking.accommodation.models.AddChangeAccommodationRequest
import com.example.bookingagent.data.networking.utilresponse.*
import java.util.GregorianCalendar

fun AddChangeAccommodationRequest.toAccommodationModel(
    idAcc: Int, idAddress: Int, rating: Float = 0f,
    category: String = ""
) =
    AccommodationEntity(
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
    AccommodationEntity(
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
        services = services.toServicesModel(),
        pictures = images!!
    )

fun RoomResponse.toRoomsModel() =
    RoomEntity(
        id = id,
        price = price,
        floor = floor,
        roomNum = roomNum,
        bedNums = bedsNum,
        occupied = occupied.toOccupiedModel(),
        images = images,
        comments = comments,
        schedule = timePrice.toScheduleModel()
    )

fun ArrayList<String>.toScheduleModel(): ArrayList<ScheduleUnit> {
    val listOfSchedule = arrayListOf<ScheduleUnit>()
    this.forEach { schedule ->
        schedule.split("~").also { splitted ->
            listOfSchedule.add(
                ScheduleUnit(
                    GregorianCalendar(), GregorianCalendar(),
                    splitted[0].toFloat()
                )
            )
        }
    }
    return listOfSchedule
}

fun ArrayList<String>.toOccupiedModel(): ArrayList<OccupiedTime> {
    val listOfOccupation = arrayListOf<OccupiedTime>()
    this.forEach { time ->
        time.split("~").also { splitted ->
            listOfOccupation.add(
                OccupiedTime(GregorianCalendar(), GregorianCalendar())
            )
        }
    }
    return listOfOccupation
}

fun ArrayList<String>.toServicesModel(): ArrayList<Service> {
        val listOfServices = arrayListOf<Service>()
        this?.forEach {
            it.split("~").also { splitted ->
                listOfServices.add(
                    Service(1, splitted[0], splitted[1], splitted[2].toFloat())
                )
            }
        }
        return listOfServices
}


fun UserResponse.toUserModel() =
    UserEntity(
        id = id,
        email = email,
        firstname = firstName,
        lastname = lastName,
        password = if (password.isNullOrBlank()) "" else password,
        username = userName,
        address = Address(
            id = address?.id,
            num = address?.num,
            zipCode = address?.zipCode,
            street = address?.street,
            city = address?.city,
            longitude = address?.longitude,
            latitude = address?.latitude
        )
    )

fun ReservationResponse.toReservationModel() =
    ReservationEntity(
        id = id,
        firstname = firstName,
        lastname = lastName,
        email = email,
        from = from,
        price = price,
        to = to,
        reservationUsed = reservationUsed,
        room = RoomEntity(
            id = room.id,
            price = room.price,
            bedNums = room.bedsNum,
            floor = room.floor,
            roomNum = room.roomNum,
            images = room.images,
            comments = room.comments,
            occupied = room.occupied.toOccupiedModel(),
            schedule = room.timePrice.toScheduleModel()
        ),
        accommodationName = accommodationName
    )

fun MessageResponse.toMessageModel() =
    MessageEntity(
        id = id,
        lastname = lastname,
        firstname = firstname,
        agentEmail = agentEmail,
        content = content,
        sender = sender
    )


fun AccommodationEntity.addId(accId: Int) =
    AccommodationEntity(
        accId, name, description, address, type, cancellingFee, cancellingDays, rating, category,
        services, pictures
    )





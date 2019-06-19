package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.networking.room.RoomApi
import com.example.bookingagent.data.networking.room.models.DeleteRoomRequest
import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomRequest
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomRequest
import com.example.bookingagent.utils.toRequest
import com.example.bookingagent.utils.toSealed
import javax.inject.Inject

class RoomRepository @Inject constructor(private val roomApi: RoomApi) {

	fun addRoom(accId: Int, room: Room) =
		roomApi.addRoom(EnvelopeAddChangeRoomRequest(room.toRequest(accId))).toSealed()

	fun editRoom(room: Room) =
		roomApi.editRoom(EnvelopeAddChangeRoomRequest(room.toRequest())).toSealed()

	fun deleteRoom(id: Long) =
		roomApi.deleteRoom(EnvelopeDeleteRoomRequest(DeleteRoomRequest(id = id.toInt()))).toSealed()

}
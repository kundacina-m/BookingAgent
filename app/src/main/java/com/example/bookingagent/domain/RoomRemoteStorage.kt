package com.example.bookingagent.domain

import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomResponse
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomResponse
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.Single

interface RoomRemoteStorage {

	fun addRoom(accId: Int, room: Room): Single<WrappedResponse<EnvelopeAddChangeRoomResponse>>
	fun editRoom(room: Room): Single<WrappedResponse<EnvelopeAddChangeRoomResponse>>
	fun deleteRoom(id: Long): Single<WrappedResponse<EnvelopeDeleteRoomResponse>>

}
package com.example.bookingagent.domain

import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomResponse
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomResponse
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.Single

interface RoomRemoteStorage {

	fun addRoom(accId: Int, roomEntity: RoomEntity): Single<WrappedResponse<EnvelopeAddChangeRoomResponse>>
	fun editRoom(roomEntity: RoomEntity): Single<WrappedResponse<EnvelopeAddChangeRoomResponse>>
	fun deleteRoom(id: Long): Single<WrappedResponse<EnvelopeDeleteRoomResponse>>

}
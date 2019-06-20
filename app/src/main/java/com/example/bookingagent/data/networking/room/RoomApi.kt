package com.example.bookingagent.data.networking.room

import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomRequest
import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomResponse
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomRequest
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RoomApi {

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("accommodation")
	fun addRoom(@Body envelopeAddChangeRoomRequest: EnvelopeAddChangeRoomRequest):
		Single<EnvelopeAddChangeRoomResponse>

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("accommodation")
	fun editRoom(@Body envelopeAddChangeRoomRequest: EnvelopeAddChangeRoomRequest):
		Single<EnvelopeAddChangeRoomResponse>

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("accommodation")
	fun deleteRoom(@Body envelopeDeleteRoomRequest: EnvelopeDeleteRoomRequest):
		Single<EnvelopeDeleteRoomResponse>
}
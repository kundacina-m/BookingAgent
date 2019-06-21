package com.example.bookingagent.data.networking.room

import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomRequest
import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomResponse
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomRequest
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomResponse
import com.example.bookingagent.utils.apiHeaders
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST

interface RoomApi {

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("accommodationResponse")
	fun addRoom(@Body envelopeAddChangeRoomRequest: EnvelopeAddChangeRoomRequest,
		@HeaderMap headers: Map<String,String> = apiHeaders.map):
		Single<EnvelopeAddChangeRoomResponse>

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("accommodationResponse")
	fun editRoom(@Body envelopeAddChangeRoomRequest: EnvelopeAddChangeRoomRequest,
		@HeaderMap headers: Map<String,String> = apiHeaders.map):
		Single<EnvelopeAddChangeRoomResponse>

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("accommodationResponse")
	fun deleteRoom(@Body envelopeDeleteRoomRequest: EnvelopeDeleteRoomRequest,
		@HeaderMap headers: Map<String,String> = apiHeaders.map):
		Single<EnvelopeDeleteRoomResponse>
}
package com.example.bookingagent.data.networking.room

import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomRequest
import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomResponse
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomRequest
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomResponse
import com.example.bookingagent.utils.ApiHeaders
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface RoomApi {

	@POST("accommodation")
	fun addRoom(@Body envelopeAddChangeRoomRequest: EnvelopeAddChangeRoomRequest,
		@HeaderMap headers: Map<String, String> = ApiHeaders.map):
		Single<EnvelopeAddChangeRoomResponse>

	@POST("accommodation")
	fun editRoom(@Body envelopeAddChangeRoomRequest: EnvelopeAddChangeRoomRequest,
		@HeaderMap headers: Map<String, String> = ApiHeaders.map):
		Single<EnvelopeAddChangeRoomResponse>

	@POST("accommodation")
	fun deleteRoom(@Body envelopeDeleteRoomRequest: EnvelopeDeleteRoomRequest,
		@HeaderMap headers: Map<String, String> = ApiHeaders.map):
		Single<EnvelopeDeleteRoomResponse>
}
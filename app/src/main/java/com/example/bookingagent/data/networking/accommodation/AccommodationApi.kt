package com.example.bookingagent.data.networking.accommodation

import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddChangeAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddChangeAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeDeleteAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeDeleteAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationResponse
import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomRequest
import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomResponse
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomRequest
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccommodationApi {

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("accommodation")
	fun addAccommodation(@Body envelopeAddChangeAccommodationRequest: EnvelopeAddChangeAccommodationRequest):
		Single<EnvelopeAddChangeAccommodationResponse>

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("getaccommodation")
	fun getAccommodations(@Body envelopeGetAccommodationsRequest: EnvelopeGetAccommodationRequest):
		Single<EnvelopeGetAccommodationResponse>

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("accommodation")
	fun deleteAccommodation(@Body envelopeDeleteAccommodationRequest: EnvelopeDeleteAccommodationRequest):
		Single<EnvelopeDeleteAccommodationResponse>

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("accommodation")
	fun editAccommodation(@Body envelopeAddChangeAccommodationRequest: EnvelopeAddChangeAccommodationRequest):
		Single<EnvelopeAddChangeAccommodationResponse>

}
package com.example.bookingagent.data.networking.accommodation

import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddRoomRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddRoomResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddServiceRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddServiceResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccommodationApi {

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("accommodation")
	fun addAccommodation(@Body envelopeAddAccommodationRequest: EnvelopeAddAccommodationRequest):
		Single<EnvelopeAddAccommodationResponse>

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("getaccommodation")
	fun getAccommodations(@Body envelopeGetAccommodationsRequest: EnvelopeGetAccommodationRequest):
		Single<EnvelopeGetAccommodationResponse>

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("accommodation")
	fun addService(@Body envelopeAddServiceRequest: EnvelopeAddServiceRequest):
		Single<EnvelopeAddServiceResponse>

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("accommodation")
	fun addRoom(@Body envelopeAddRoomRequest: EnvelopeAddRoomRequest):
		Single<EnvelopeAddRoomResponse>

}
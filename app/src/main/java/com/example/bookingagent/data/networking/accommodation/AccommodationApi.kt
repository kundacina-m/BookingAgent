package com.example.bookingagent.data.networking.accommodation

import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddChangeAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddChangeAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeDeleteAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeDeleteAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationResponse
import com.example.bookingagent.utils.apiHeaders
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AccommodationApi {

	@POST("accommodation")
	fun addAccommodation(@Body envelopeAddChangeAccommodationRequest: EnvelopeAddChangeAccommodationRequest,
		@HeaderMap headers: Map<String, String> = apiHeaders.map):
		Single<EnvelopeAddChangeAccommodationResponse>

	@POST("getaccommodation")
	fun getAccommodations(@Body envelopeGetAccommodationsRequest: EnvelopeGetAccommodationRequest,
		@HeaderMap headers: Map<String, String> = apiHeaders.map):
		Single<EnvelopeGetAccommodationResponse>

	@POST("accommodation")
	fun deleteAccommodation(@Body envelopeDeleteAccommodationRequest: EnvelopeDeleteAccommodationRequest,
		@HeaderMap headers: Map<String, String> = apiHeaders.map):
		Single<EnvelopeDeleteAccommodationResponse>

	@POST("accommodation")
	fun editAccommodation(@Body envelopeAddChangeAccommodationRequest: EnvelopeAddChangeAccommodationRequest,
		@HeaderMap headers: Map<String, String> = apiHeaders.map):
		Single<EnvelopeAddChangeAccommodationResponse>

}
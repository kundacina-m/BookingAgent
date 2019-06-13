package com.example.bookingagent.data.networking.accommodation

import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationsRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationsResponse
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
	fun getAccommodations(@Body	 envelopeGetAccommodationsRequest: EnvelopeGetAccommodationsRequest) :
		Single<EnvelopeGetAccommodationsResponse>

}
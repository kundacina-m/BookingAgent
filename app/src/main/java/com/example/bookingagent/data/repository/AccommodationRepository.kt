package com.example.bookingagent.data.repository

import com.example.bookingagent.data.networking.accommodation.AccommodationApi
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationsRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationsResponse
import com.example.bookingagent.data.networking.accommodation.models.GetAccommodationsRequest
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.toSealed
import io.reactivex.Single
import javax.inject.Inject

class AccommodationRepository @Inject constructor(private val accommodationApi: AccommodationApi) {

	fun addAccommodation(envelopeAddAccommodationRequest: EnvelopeAddAccommodationRequest):
		Single<WrappedResponse<EnvelopeAddAccommodationResponse>> =
		accommodationApi.addAccommodation(envelopeAddAccommodationRequest).toSealed()

	fun getAccommodations(): Single<WrappedResponse<EnvelopeGetAccommodationsResponse>> =
		accommodationApi.getAccommodations(EnvelopeGetAccommodationsRequest(GetAccommodationsRequest())).toSealed()
}
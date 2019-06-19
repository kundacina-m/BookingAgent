package com.example.bookingagent.domain

import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddChangeAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeDeleteAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationResponse
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.Single

interface AccommodationRemoteStorage {

	fun addAccommodation(accommodation: Accommodation): Single<WrappedResponse<EnvelopeAddChangeAccommodationResponse>>
	fun getAccommodations(): Single<WrappedResponse<EnvelopeGetAccommodationResponse>>
	fun deleteAccommodation(id: Int): Single<WrappedResponse<EnvelopeDeleteAccommodationResponse>>
	fun editAccommodation(accommodation: Accommodation): Single<WrappedResponse<EnvelopeAddChangeAccommodationResponse>>

}
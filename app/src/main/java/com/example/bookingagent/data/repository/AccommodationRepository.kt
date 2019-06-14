package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.AccommodationDao
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.db.entities.Address
import com.example.bookingagent.data.networking.accommodation.AccommodationApi
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddServiceRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddServiceResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationsRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationsResponse
import com.example.bookingagent.data.networking.accommodation.models.GetAccommodationsRequest
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.toSealed
import io.reactivex.Single
import javax.inject.Inject

class AccommodationRepository @Inject constructor(
	private val accommodationDao: AccommodationDao,
	private val accommodationApi: AccommodationApi) {

	fun addAccommodation(envelopeAddAccommodationRequest: EnvelopeAddAccommodationRequest):
		Single<WrappedResponse<EnvelopeAddAccommodationResponse>> =
		accommodationApi.addAccommodation(envelopeAddAccommodationRequest).toSealed()

	fun getAccommodations(): Single<WrappedResponse<EnvelopeGetAccommodationsResponse>> =
		accommodationApi.getAccommodations(EnvelopeGetAccommodationsRequest(GetAccommodationsRequest())).toSealed()

	fun addService(
		envelopeAddServiceRequest: EnvelopeAddServiceRequest): Single<WrappedResponse<EnvelopeAddServiceResponse>> =
		accommodationApi.addService(envelopeAddServiceRequest).toSealed()

	fun addAccommodationToDB(accommodation: Accommodation): Single<WrappedResponse<Long>> =
		Single.just(accommodationDao.addAccommodation(accommodation)).toSealed()

	fun addAddressToDB(address: Address): Single<WrappedResponse<Long>> =
		Single.just(accommodationDao.addAddress(address)).toSealed()

}
package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.AccommodationDao
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.db.entities.Address
import com.example.bookingagent.data.networking.accommodation.AccommodationApi
import com.example.bookingagent.data.networking.accommodation.models.DeleteAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddChangeAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeDeleteAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.GetAccommodationRequest
import com.example.bookingagent.utils.toRequest
import com.example.bookingagent.utils.toSealed
import io.reactivex.Single
import javax.inject.Inject

class AccommodationRepository @Inject constructor(
	private val accommodationDao: AccommodationDao,
	private val accommodationApi: AccommodationApi
) {

	fun addAccommodation(accommodation: Accommodation) =
		accommodationApi.addAccommodation(EnvelopeAddChangeAccommodationRequest(accommodation.toRequest())).toSealed()

	fun getAccommodations() =
		accommodationApi.getAccommodations(EnvelopeGetAccommodationRequest(GetAccommodationRequest())).toSealed()

	fun deleteAccommodation(id: Int) =
		accommodationApi.deleteAccommodation(
			EnvelopeDeleteAccommodationRequest(DeleteAccommodationRequest(id = id))).toSealed()

	fun editAccommodation(accommodation: Accommodation) =
		accommodationApi.editAccommodation(EnvelopeAddChangeAccommodationRequest(accommodation.toRequest())).toSealed()



	fun addAccommodationToDB(accommodation: Accommodation) =
		Single.just(accommodationDao.addAccommodation(accommodation)).toSealed()

	fun addAddressToDB(address: Address) =
		Single.just(accommodationDao.addAddress(address)).toSealed()
}
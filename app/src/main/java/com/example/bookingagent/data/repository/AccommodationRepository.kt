package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.AccommodationDao
import com.example.bookingagent.data.db.entities.AccommodationEntity
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

	// region NETWORK

	fun addAccommodation(accommodationEntity: AccommodationEntity) =
		accommodationApi.addAccommodation(
			EnvelopeAddChangeAccommodationRequest(accommodationEntity.toRequest())).toSealed()

	fun getAccommodations() =
		accommodationApi.getAccommodations(EnvelopeGetAccommodationRequest(GetAccommodationRequest())).toSealed()

	fun deleteAccommodation(id: Int) =
		accommodationApi.deleteAccommodation(
			EnvelopeDeleteAccommodationRequest(DeleteAccommodationRequest(id = id))).toSealed()

	fun editAccommodation(accommodationEntity: AccommodationEntity) =
		accommodationApi.editAccommodation(
			EnvelopeAddChangeAccommodationRequest(accommodationEntity.toRequest())).toSealed()

	// endregion NETWORK

	// region DB

	fun addAccommodationToDB(accommodationEntity: AccommodationEntity) =
		Single.just(accommodationDao.addAccommodation(accommodationEntity)).toSealed()

	fun getAllAccommodationFromDB() =
		accommodationDao.getAllAccommodation().toSealed()

	fun deleteAccommodationFromDB(id: Int) =
		Single.just(accommodationDao.deleteAccommodation(id)).toSealed()

	fun updateAccommodationInDB(accommodationEntity: AccommodationEntity) =
		Single.just(accommodationDao.updateAccommodation(accommodationEntity)).toSealed()

	fun getAccommodationFromDB(id: Int) =
		accommodationDao.getAccommodation(id).toSealed()

	// endregion DB

}
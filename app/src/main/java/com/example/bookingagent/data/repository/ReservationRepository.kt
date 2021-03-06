package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.ReservationDao
import com.example.bookingagent.data.db.entities.ReservationEntity
import com.example.bookingagent.data.networking.reservation.ReservationApi
import com.example.bookingagent.data.networking.reservation.models.EnvelopeGetReservationRequest
import com.example.bookingagent.data.networking.reservation.models.EnvelopeSuccessfulReservationRequest
import com.example.bookingagent.data.networking.reservation.models.GetReservationRequest
import com.example.bookingagent.data.networking.reservation.models.SuccessfulReservationRequest
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.toSealed
import io.reactivex.Single
import javax.inject.Inject

class ReservationRepository @Inject constructor(
	private val reservationApi: ReservationApi,
	private val reservationDao: ReservationDao
) {

	fun getAllReservations() =
		reservationApi.getAllReservations(EnvelopeGetReservationRequest(GetReservationRequest())).toSealed()

	fun addReservationToDB(reservation: ReservationEntity): Single<WrappedResponse<Long>> =
		Single.just(reservationDao.addReservation(reservation)).toSealed()

	fun getAllReservationsFromDB() =
		reservationDao.getAllReservations().toSealed()

	fun reservationUsed(id: Int) =
		reservationApi.successfulReservation(
			EnvelopeSuccessfulReservationRequest(SuccessfulReservationRequest(id))).toSealed()

	fun updateReservation(id: Int) =
		reservationDao.updateUsedInReservation(id, true)

	fun deleteAllReservations() =
		reservationDao.nukeReservationTable()
}
package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.ReservationDao
import com.example.bookingagent.data.networking.reservation.ReservationApi
import com.example.bookingagent.data.networking.reservation.models.EnvelopeGetReservationRequest
import com.example.bookingagent.data.networking.reservation.models.GetReservationRequest
import com.example.bookingagent.utils.toSealed
import javax.inject.Inject

class ReservationRepository @Inject constructor(private val reservationApi: ReservationApi, val reservationDao: ReservationDao) {

	fun getAllReservations() =
		reservationApi.getAllReservations(EnvelopeGetReservationRequest(GetReservationRequest())).toSealed()

}
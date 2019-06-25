package com.example.bookingagent.screens.reservations.list

import base.BaseViewModel
import com.example.bookingagent.data.repository.ReservationRepository
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReservationsViewModel @Inject constructor(val reservationRepository: ReservationRepository) : BaseViewModel() {
	fun getAllReservations() {
		disposables.add(reservationRepository.getAllReservations()
			.subscribeOn(Schedulers.io())
			.subscribeBy {

			})
	}
}
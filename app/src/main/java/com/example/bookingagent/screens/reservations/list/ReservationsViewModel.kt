package com.example.bookingagent.screens.reservations.list

import base.BaseViewModel
import com.example.bookingagent.data.repository.ReservationRespository
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReservationsViewModel @Inject constructor(val reservationRespository: ReservationRespository) : BaseViewModel() {
	fun getAllReservations() {
		disposables.add(reservationRespository.getAllReservations()
			.subscribeOn(Schedulers.io())
			.subscribeBy {

			})
	}
}
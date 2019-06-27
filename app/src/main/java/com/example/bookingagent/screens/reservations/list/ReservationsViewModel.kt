package com.example.bookingagent.screens.reservations.list

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.ReservationEntity
import com.example.bookingagent.data.repository.MessagesRepository
import com.example.bookingagent.data.repository.ReservationRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReservationsViewModel @Inject constructor(
	val reservationRepository: ReservationRepository,
	val messagesRepository: MessagesRepository
) : BaseViewModel() {

	val reservations = MutableLiveData<WrappedResponse<List<ReservationEntity>>>()

	fun getAllReservations() =
		disposables.add(reservationRepository.getAllReservationsFromDB()
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				reservations.postValue(it)
			})
}
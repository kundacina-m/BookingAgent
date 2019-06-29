package com.example.bookingagent.screens.reservations.list

import androidx.lifecycle.MutableLiveData
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType.CONNECTED
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.ReservationEntity
import com.example.bookingagent.data.repository.MessagesRepository
import com.example.bookingagent.data.repository.ReservationRepository
import com.example.bookingagent.utils.RequestError.NoInternetError
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReservationsViewModel @Inject constructor(
	val reservationRepository: ReservationRepository,
	val messagesRepository: MessagesRepository
) : BaseViewModel() {

	val reservations = MutableLiveData<WrappedResponse<List<ReservationEntity>>>()
	val reservationUsed = MutableLiveData<WrappedResponse<Boolean>>()

	fun getAllReservations() =
		disposables.add(reservationRepository.getAllReservationsFromDB()
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				reservations.postValue(it)
			})

	fun reservationUsed(id: Int) {
		disposables.add(reservationRepository.reservationUsed(id)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				when (it) {
					is WrappedResponse.OnSuccess -> {
						reservationUsed.postValue(WrappedResponse.OnSuccess(true))
						updateUsedInDB(id)
					}
					is WrappedResponse.OnError -> {
						if (it.error is NoInternetError) {
							WorkManager.getInstance().enqueue(
								OneTimeWorkRequestBuilder<UseReservationWorker>()
									.setInputData(createInputDataForInput(id))
									.setConstraints(
										Constraints.Builder()
											.setRequiredNetworkType(CONNECTED).build()
									).build()
							)
						}
						reservationUsed.postValue(WrappedResponse.OnError(it.error))
						updateUsedInDB(id)
					}
				}
			})
	}

	private fun createInputDataForInput(id: Int) =
		Data.Builder().run {
			putInt("resId", id)
		}.build()

	private fun updateUsedInDB(id: Int) =
		reservationRepository.updateReservation(id)

}
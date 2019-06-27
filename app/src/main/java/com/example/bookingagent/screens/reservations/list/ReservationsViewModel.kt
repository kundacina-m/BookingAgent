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
                    is WrappedResponse.OnError -> reservationUsed.postValue(WrappedResponse.OnError(it.error))
                }
            })
    }

    private fun updateUsedInDB(id: Int) =
        reservationRepository.updateReservation(id)

}
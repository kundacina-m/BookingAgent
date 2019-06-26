package com.example.bookingagent.screens.reservations.list

import android.util.Log
import base.BaseViewModel
import com.example.bookingagent.data.networking.utilresponse.ReservationResponse
import com.example.bookingagent.data.repository.MessagesRepository
import com.example.bookingagent.data.repository.ReservationRepository
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.toMessageModel
import com.example.bookingagent.utils.toReservationModel
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class ReservationsViewModel @Inject constructor(
    val reservationRepository: ReservationRepository,
    val messagesRepository: MessagesRepository
) : BaseViewModel() {
    fun getAllReservations() {
        disposables.add(reservationRepository.getAllReservations()
            .subscribeOn(Schedulers.io())
            .subscribeBy {
                when (it) {
                    is WrappedResponse.OnSuccess -> mapToDB(it.item.body.body.reservationsResponse)
                    is WrappedResponse.OnError -> Log.d("ERROR", it.error.toString())
                }
            })
    }

    private fun mapToDB(response: ArrayList<ReservationResponse>?) {
        disposables.add(Single.just(reservationRepository)
            .subscribeOn(Schedulers.io())
            .subscribeBy { repository ->
                response?.forEach { reservationResponse ->
                    repository.addReservationToDB(reservationResponse.toReservationModel())
                        .subscribeOn(Schedulers.io())
                        .subscribeBy {
                            when (it) {
                                is WrappedResponse.OnSuccess -> reservationResponse.messages.forEach { messageResponse ->
                                    messagesRepository.addMessage(
                                        reservationResponse.id,
                                        messageResponse.toMessageModel()
                                    )

                                }
                            }
                        }
                }
            })
    }
}
package com.example.bookingagent.screens.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationResponse
import com.example.bookingagent.data.networking.utilresponse.AccommodationResponse
import com.example.bookingagent.data.networking.utilresponse.ReservationResponse
import com.example.bookingagent.data.repository.*
import com.example.bookingagent.utils.*
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val accommodationRepository: AccommodationRepository,
    private val roomRepository: RoomRepository,
    private val reservationRepository: ReservationRepository,
    private val messagesRepository: MessagesRepository,
    private val userRepository: UserRepository
) :
    BaseViewModel() {

    val accommodationSyncingStatus = MutableLiveData<WrappedResponse<Boolean>>()
    val reservationSyncingStatus = MutableLiveData<WrappedResponse<Boolean>>()


    fun getAccommodations() =
        disposables.add(
            accommodationRepository.getAccommodations()
                .subscribeOn(Schedulers.io())
                .subscribeBy {
                    when (it) {
                        is OnSuccess -> {
                            wipeOldLocalAccommodations()
                            mapResponseToDB(it.item)
                            accommodationSyncingStatus.postValue(OnSuccess(true))
                        }
                        is OnError -> {
                            accommodationSyncingStatus.postValue(OnError(it.error))
                        }
                    }
                })

    private fun wipeOldLocalAccommodations() {
        accommodationRepository.deleteAllAccommodation()
        roomRepository.deleteAllRooms()
    }

    private fun mapResponseToDB(response: EnvelopeGetAccommodationResponse) =
        response.body.body.accommodationResponse?.forEach { accommodationResponse ->
            addAccommodationToDB(accommodationResponse)
        }

    private fun addAccommodationToDB(accommodationResponseResponse: AccommodationResponse) =
        disposables.add(
            accommodationRepository.addAccommodationToDB(accommodationResponseResponse.toAccommodationModel())
                .subscribeOn(Schedulers.io())
                .subscribeBy {
                    if ((it as OnSuccess).item > 0) addRoomsToDB(accommodationResponseResponse)
                })

    private fun addRoomsToDB(accommodationResponseResponse: AccommodationResponse) =
        accommodationResponseResponse.rooms?.forEach { roomResponse ->
            disposables.add(roomRepository.addRoomToDB(roomResponse.toRoomsModel())
                .subscribeOn(Schedulers.io())
                .subscribeBy {
                   if (it is OnSuccess) roomRepository.addAccRoomRow(accommodationResponseResponse.id, roomResponse.id)
                })
        }

    fun getAllReservations() =
        disposables.add(reservationRepository.getAllReservations()
            .subscribeOn(Schedulers.io())
            .subscribeBy {
                when (it) {
                    is OnSuccess -> {
                        wipeOldLocalReservationAndMessages()
                        mapReservationsAndMessagesToDB(it.item.body.body.reservationsResponse)
                        reservationSyncingStatus.postValue(OnSuccess(true))
                    }
                    is OnError -> reservationSyncingStatus.postValue(OnError(it.error))
                }
            })

    private fun wipeOldLocalReservationAndMessages() {
        reservationRepository.deleteAllReservations()
        messagesRepository.deleteAllMessages()
    }


    private fun mapReservationsAndMessagesToDB(response: ArrayList<ReservationResponse>?) =
        disposables.add(Single.just(reservationRepository)
            .subscribeOn(Schedulers.io())
            .subscribeBy { repository ->
                response?.forEach { reservationResponse ->
                    repository.addReservationToDB(reservationResponse.toReservationModel())
                        .subscribeOn(Schedulers.io())
                        .subscribeBy {
                            when (it) {
                                is OnSuccess -> reservationResponse.messages.forEach { messageResponse ->
                                    messagesRepository.addMessage(
                                        reservationResponse.id,
                                        messageResponse.toMessageModel()
                                    )

                                }
                            }
                        }
                }
            })

    fun getUserInfo() =
        disposables.add(
            userRepository.getUserProfile()
                .subscribeOn(Schedulers.io())
                .subscribeBy {
                    when (it) {
                        is OnSuccess -> userRepository.addUser(it.item.body.body.userResponse?.toUserModel()!!)
                        is OnError -> Log.d("bad mapping bro", "ERROR")
                    }
                })


}
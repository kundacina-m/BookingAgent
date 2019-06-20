package com.example.bookingagent.screens.accommodations.add

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import com.example.bookingagent.utils.addId
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddAccommodationViewModel @Inject constructor(private val accommodationRepository: AccommodationRepository) :
	BaseViewModel() {

	val addingAccommodation = MutableLiveData<WrappedResponse<Boolean>>()

	fun addAccommodation(accommodation: Accommodation) =
		disposables.add(
			accommodationRepository.addAccommodation(accommodation)
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					when (it) {
						is OnSuccess -> addAccommodationToDB(accommodation.addId(it.item.body.body.idAccommodation!!))
						is OnError -> addingAccommodation.postValue(OnError(it.error))
					}
				})

	private fun addAccommodationToDB(accommodation: Accommodation) =
		disposables.add(Single.just(accommodationRepository)
			.subscribeOn(Schedulers.io())
			.subscribeBy { repository ->
				repository.addAccommodationToDB(accommodation).subscribeBy {
					when (it) {
						is OnSuccess -> addingAccommodation.postValue(OnSuccess(true))
						is OnError -> addingAccommodation.postValue(OnError(it.error))
					}
				}
			}
		)
}
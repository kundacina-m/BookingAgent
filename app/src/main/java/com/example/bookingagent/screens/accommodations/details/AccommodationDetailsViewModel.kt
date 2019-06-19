package com.example.bookingagent.screens.accommodations.details

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccommodationDetailsViewModel @Inject constructor(private val accommodationRepository: AccommodationRepository) :
	BaseViewModel() {

	val isDeleted = MutableLiveData<WrappedResponse<Any>>()

	fun deleteAccommodation(id: Int) {
		disposables.add(accommodationRepository.deleteAccommodation(id)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				isDeleted.postValue(it)
			}
		)
	}
}
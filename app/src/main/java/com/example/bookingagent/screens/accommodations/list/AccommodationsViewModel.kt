package com.example.bookingagent.screens.accommodations.list

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationResponse
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccommodationsViewModel @Inject constructor(val accommodationRepository: AccommodationRepository) :
	BaseViewModel() {

	val accommodations
		by lazy { MutableLiveData<WrappedResponse<EnvelopeGetAccommodationResponse>>() }

	fun getAccommodations() {
		disposables.add(
			accommodationRepository.getAccommodations()
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					accommodations.postValue(it)
				})
	}
}
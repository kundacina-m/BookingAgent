package com.example.bookingagent.screens.addaccommodation

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationResponse
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddAccommodationViewModel @Inject constructor(private val accommodationRepository: AccommodationRepository) :
	BaseViewModel() {

	val addingAccommodation = MutableLiveData<WrappedResponse<EnvelopeAddAccommodationResponse>>()

	fun addAccommodation(envelopeAddAccommodationRequest: EnvelopeAddAccommodationRequest) {
		disposables.add(
			accommodationRepository.addAccommodation(envelopeAddAccommodationRequest)
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					addingAccommodation.postValue(it)
				})
	}
}
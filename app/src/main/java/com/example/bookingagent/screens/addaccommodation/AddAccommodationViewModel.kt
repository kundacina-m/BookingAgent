package com.example.bookingagent.screens.addaccommodation

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationResponse
import com.example.bookingagent.data.repository.UserRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddAccommodationViewModel @Inject constructor(val repository: UserRepository) : BaseViewModel() {

	val addingAccommodation = MutableLiveData<WrappedResponse<EnvelopeAddAccommodationResponse>>()

	fun addAccommodation(envelopeAddAccommodationRequest: EnvelopeAddAccommodationRequest) {
		disposables.add(
			repository.addAccommodation(envelopeAddAccommodationRequest).subscribeOn(Schedulers.io()).subscribeBy {
				addingAccommodation.postValue(it)
			})
	}
}
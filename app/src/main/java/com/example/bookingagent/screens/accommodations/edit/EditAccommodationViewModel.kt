package com.example.bookingagent.screens.accommodations.edit

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddChangeAccommodationResponse
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditAccommodationViewModel @Inject constructor(private val accommodationRepository: AccommodationRepository) :
	BaseViewModel() {

	val editStatus = MutableLiveData<WrappedResponse<EnvelopeAddChangeAccommodationResponse>>()

	fun editAccommodation(accommodation: Accommodation) {
		disposables.add(
			accommodationRepository.editAccommodation(accommodation)
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					editStatus.postValue(it)
				})
	}
}
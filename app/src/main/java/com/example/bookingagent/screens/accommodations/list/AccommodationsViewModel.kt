package com.example.bookingagent.screens.accommodations.list

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccommodationsViewModel @Inject constructor(val accommodationRepository: AccommodationRepository) :
	BaseViewModel() {

	val accommodations
		by lazy { MutableLiveData<WrappedResponse<List<Accommodation>>>() }

	fun getAccommodations() {
		disposables.add(accommodationRepository.getAllAccommodationFromDB()
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				accommodations.postValue(it)
			})
	}
}
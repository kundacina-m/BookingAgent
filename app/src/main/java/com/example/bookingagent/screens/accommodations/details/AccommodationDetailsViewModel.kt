package com.example.bookingagent.screens.accommodations.details

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccommodationDetailsViewModel @Inject constructor(private val accommodationRepository: AccommodationRepository) :
	BaseViewModel() {

	val accommodation = MutableLiveData<WrappedResponse<Accommodation>>()
	val deletingStatus = MutableLiveData<WrappedResponse<Boolean>>()

	fun deleteAccommodation(id: Int) =
		disposables.add(accommodationRepository.deleteAccommodation(id)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				when (it) {
					is OnSuccess -> deleteAccommodationFromDB(id)
					is OnError -> deletingStatus.postValue(OnError(it.error))
				}
			}
		)

	fun getAccommodationDetails(id: Int) =
		disposables.add(accommodationRepository.getAccommodationFromDB(id)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				accommodation.postValue(it)
			})

	private fun deleteAccommodationFromDB(id: Int) =
		disposables.add(
			Single.just(accommodationRepository)
				.subscribeOn(Schedulers.io())
				.subscribeBy { repository ->
					repository.deleteAccommodationFromDB(id)
						.subscribeOn(Schedulers.io())
						.subscribeBy {
							when (it) {
								is OnSuccess -> deletingStatus.postValue(OnSuccess(it.item > 0))
								is OnError -> deletingStatus.postValue(OnError(it.error))
							}
						}
				}
		)
}
package com.example.bookingagent.screens.accommodations.edit

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.AccommodationEntity
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditAccommodationViewModel @Inject constructor(private val accommodationRepository: AccommodationRepository) :
	BaseViewModel() {

	val images = MutableLiveData<List<String>>()
	val editStatus = MutableLiveData<WrappedResponse<Boolean>>()

	fun editAccommodation(accommodationEntity: AccommodationEntity) =
		disposables.add(
			accommodationRepository.editAccommodation(accommodationEntity)
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					when (it) {
						is OnSuccess -> updateAccommodationInDB(accommodationEntity)
						is OnError -> editStatus.postValue(OnError(it.error))
					}
				})

	private fun updateAccommodationInDB(accommodationEntity: AccommodationEntity) =
		disposables.add(
			Single.just(accommodationRepository)
				.subscribeOn(Schedulers.io())
				.subscribeBy { repository ->
					repository.updateAccommodationInDB(accommodationEntity)
						.subscribeOn(Schedulers.io())
						.subscribeBy {
							when (it) {
								is OnSuccess -> editStatus.postValue(OnSuccess(it.item > 0))
								is OnError -> editStatus.postValue(OnError(it.error))
							}
						}
				}
		)
}
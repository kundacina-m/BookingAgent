package com.example.bookingagent.screens.accommodations.add

import android.util.Log
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.AccommodationEntity
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

	val images = MutableLiveData<List<String>>()
	val addingAccommodation = MutableLiveData<WrappedResponse<Boolean>>()

	fun addAccommodation(accommodationEntity: AccommodationEntity) =
		disposables.add(
			accommodationRepository.addAccommodation(accommodationEntity)
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					when (it) {
						is OnSuccess -> addAccommodationToDB(
							accommodationEntity.addId(it.item.body.body.idAccommodation!!))
						is OnError -> {
							Log.d("ERROR", "addAccommodation: ")
						}
					}
				})

	private fun addAccommodationToDB(accommodationEntity: AccommodationEntity) =
		disposables.add(Single.just(accommodationRepository)
			.subscribeOn(Schedulers.io())
			.subscribeBy { repository ->
				repository.addAccommodationToDB(accommodationEntity).subscribeBy {
					when (it) {
						is OnSuccess -> addingAccommodation.postValue(OnSuccess(true))
						is OnError -> addingAccommodation.postValue(OnError(it.error))
					}
				}
			}
		)

}
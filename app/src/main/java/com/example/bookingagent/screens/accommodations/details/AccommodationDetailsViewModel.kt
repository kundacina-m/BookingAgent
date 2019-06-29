package com.example.bookingagent.screens.accommodations.details

import androidx.lifecycle.MutableLiveData
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType.CONNECTED
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.AccommodationEntity
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.utils.RequestError.NoInternetError
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccommodationDetailsViewModel @Inject constructor(private val accommodationRepository: AccommodationRepository) :
	BaseViewModel() {

	val accommodation = MutableLiveData<WrappedResponse<AccommodationEntity>>()
	val deletingStatus = MutableLiveData<WrappedResponse<Boolean>>()

	fun deleteAccommodation(id: Int) =
		disposables.add(accommodationRepository.deleteAccommodation(id)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				when (it) {
					is OnSuccess -> deleteAccommodationFromDB(id)
					is OnError -> {
						if (it.error is NoInternetError) {
							WorkManager.getInstance().enqueue(
								OneTimeWorkRequestBuilder<DeleteAccommodationWorker>()
									.setInputData(createInputDataForInput(id))
									.setConstraints(
										Constraints.Builder()
											.setRequiredNetworkType(CONNECTED).build()
									).build()
							)
						}
						deletingStatus.postValue(OnError(it.error))
						deleteAccommodationFromDB(id)
					}
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

	private fun createInputDataForInput(id: Int) =
		Data.Builder().run {
			putInt("accId", id)
		}.build()
}
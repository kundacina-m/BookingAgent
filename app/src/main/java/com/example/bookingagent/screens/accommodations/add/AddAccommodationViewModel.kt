package com.example.bookingagent.screens.accommodations.add

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.db.entities.Address
import com.example.bookingagent.data.networking.accommodation.models.AddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddServiceRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddServiceResponse
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddAccommodationViewModel @Inject constructor(private val accommodationRepository: AccommodationRepository) :
	BaseViewModel() {

	val addingAccommodation = MutableLiveData<WrappedResponse<EnvelopeAddAccommodationResponse>>()
	val addingService = MutableLiveData<WrappedResponse<EnvelopeAddServiceResponse>>()

	fun addAccommodation(addAccommodationRequest: AddAccommodationRequest) {
		disposables.add(
			accommodationRepository.addAccommodation(EnvelopeAddAccommodationRequest(addAccommodationRequest))
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					if (it is OnSuccess) {
						val response = it.item.body.body
						addingAccommodation.postValue(it)

						// TODO map response to DB
					}
				})
	}

	fun addService(envelopeAddServiceRequest: EnvelopeAddServiceRequest) {
		disposables.add(
			accommodationRepository.addService(envelopeAddServiceRequest)
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					addingService.postValue(it)
				}
		)
	}

	private fun addAccommodationToDB(accommodation: Accommodation) =
		disposables.add(Single.just(accommodationRepository)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				it.addAccommodationToDB(accommodation).subscribeBy {

				}
			}
		)

	private fun addAddressToDB(address: Address) =
		disposables.add(Single.just(accommodationRepository)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				it.addAddressToDB(address).subscribeBy {

				}
			}
		)
}
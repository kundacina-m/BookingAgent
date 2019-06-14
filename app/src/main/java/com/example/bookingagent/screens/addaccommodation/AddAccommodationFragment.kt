package com.example.bookingagent.screens.addaccommodation

import android.util.Log
import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.networking.accommodation.models.AddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.AddServiceRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddServiceRequest
import com.example.bookingagent.data.networking.common.AddressSOAP
import com.example.bookingagent.utils.RequestError
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_add_accommodation.btAddAccommodation

class AddAccommodationFragment : BaseFragment<AddAccommodationViewModel, AddAccommodationRoutes>() {

	override fun getLayoutId(): Int = R.layout.fragment_add_accommodation

	override fun setObservers() {

		viewModel.addingAccommodation.observe(this, Observer {
			when (it) {
				is OnSuccess -> {
					val response = it.item.body.body
					addService(response.idAccommodation)
				}
				is OnError -> Log.d(TAG, "initView: OnError" + (it.error as RequestError.UnknownError).error.message)
			}
		})
	}

	override fun initView() {

		btAddAccommodation.setOnClickListener {
			addAccommodation()
		}

	}

	private fun addAccommodation() {
		viewModel.addAccommodation(
				AddAccommodationRequest(
					"hotel", "Pravo dobar hotel", "SMESTAJ_HOTEL",
					AddressSOAP(1, 1, "idk", 1, "idk", 2), 0, 50,
					arrayListOf("slika1", "slika2", "slika3")
				)
		)
	}

	private fun addService(idAccommodation: Int) {
		viewModel.addService(
			EnvelopeAddServiceRequest(
				AddServiceRequest(idAccommodation.toLong(), "MASAZA", "dobra masaza", 50)
			)
		)
	}

}
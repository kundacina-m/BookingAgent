package com.example.bookingagent.screens.accommodations.add

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
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
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

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

		actionBarSetup()

		btAddAccommodation.setOnClickListener {
			addAccommodation()
		}

	}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
		actionBar?.title = ""
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		super.onCreateOptionsMenu(menu, inflater)
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
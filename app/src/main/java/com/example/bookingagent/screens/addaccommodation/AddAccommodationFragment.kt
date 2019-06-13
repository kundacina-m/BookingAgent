package com.example.bookingagent.screens.addaccommodation

import android.util.Log
import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.networking.accommodation.models.AddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.common.AddressSOAP
import com.example.bookingagent.data.networking.common.ServiceSOAP
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_add_accommodation.btAddAccommodation

class AddAccommodationFragment : BaseFragment<AddAccommodationViewModel, AddAccommodationRoutes>() {


	override fun getLayoutId(): Int = R.layout.fragment_add_accommodation

	override fun setObservers() {

		viewModel.addingAccommodation.observe(this, Observer {
			when (it) {
				is OnSuccess -> Log.d(TAG, "initView: OnSuccess")
				is OnError -> Log.d(TAG, "initView: OnError")
			}
		})
	}

	override fun initView() {

		btAddAccommodation.setOnClickListener {
			viewModel.addAccommodation(
				EnvelopeAddAccommodationRequest(
					AddAccommodationRequest(
						"hotel", "Pravo dobar hotel", "SMESTAJ_HOTEL",
						AddressSOAP(1, 1, "idk", 1, "idk", 2), 0, 50,
						arrayListOf("slika1", "slika2", "slika3")
					)
				)
			)
		}



	}

}
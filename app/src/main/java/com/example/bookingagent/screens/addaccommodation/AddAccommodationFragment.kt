package com.example.bookingagent.screens.addaccommodation

import android.util.Log
import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.networking.accommodation.models.AddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.AddRoomRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.common.AddressRequest
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_add_accommodation.btAddAccommodation

class AddAccommodationFragment : BaseFragment<AddAccommodationViewModel, AddAccommodationRoutes>() {

	override fun getLayoutId(): Int = R.layout.fragment_add_accommodation

	override fun initView() {

		btAddAccommodation.setOnClickListener {
			viewModel.addAccommodation(
				EnvelopeAddAccommodationRequest(
					AddAccommodationRequest(
						"hotel", "Pravo dobar hotel", "Raj", AddressRequest(1, 1, "NS", 1, "1", 2)
//						listOf(AddRoomRequest(
//							13,
//							42,
//							1,
//							666,
//							listOf("slika1", "slika2", "slika3", "slika4", "slika5")
//						))
					)
				)
			)
		}

		viewModel.addingAccommodation.observe(this, Observer {
			when (it) {
				is OnSuccess -> Log.d(TAG, "initView: OnSuccess")
				is OnError -> Log.d(TAG, "initView: OnError")
			}
		})

	}

}
package com.example.bookingagent.screens.addaccommodation

import android.util.Log
import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.networking.accommodation.models.AddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.AddRoom
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_add_accommodation.btAddAccommodation

class AddAccommodationFragment : BaseFragment<AddAccommodationViewModel, AddAccommodationRoutes>() {

	override fun getLayoutId(): Int = R.layout.fragment_add_accommodation

	override fun initView() {

		btAddAccommodation.setOnClickListener {
			viewModel.addAccommodation(EnvelopeAddAccommodationRequest(AddAccommodationRequest(",",",",",",",",
				AddRoom(1,2,3,4, listOf("slika")))))
		}

		viewModel.addingAccommodation.observe(this, Observer {
			when (it) {
				is OnSuccess -> Log.d(TAG, "initView: OnSuccess")
				is OnError -> Log.d(TAG, "initView: OnError")
			}
		})

	}

}
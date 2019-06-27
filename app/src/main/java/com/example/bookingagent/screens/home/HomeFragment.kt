package com.example.bookingagent.screens.home

import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R

class HomeFragment : BaseFragment<HomeViewModel, HomeRoutes>() {

	override fun getLayoutId(): Int = R.layout.fragment_home

	override fun setObservers() {

		viewModel.syncingStatus.observe(this, Observer {
			if (it) syncedWithBackend() else failedSyncing()
		})

	}

	override fun initView() {
		fetchData()
	}

	private fun fetchData() {
		viewModel.getAccommodations()
		viewModel.getAllReservations()
	}

	private fun failedSyncing() {

	}

	private fun syncedWithBackend() {

	}
}
package com.example.bookingagent.screens.accommodations

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_accommodation.rvAccommodation

class AccommodationsFragment : BaseFragment<AccommodationsViewModel, AccommodationsRoutes>() {

	private val adapter by lazy {
		AccommodationsAdapter()
	}

	override fun getLayoutId(): Int = R.layout.fragment_accommodation

	override fun setObservers() {
		viewModel.accommodations.observe(this, Observer {
			when (it) {
				is OnSuccess -> Log.d(TAG, "initView: OnSuccess")
				is OnError -> Log.d(TAG, "initView: OnError")
			}
		})
	}

	override fun initView() {

		setupRecyclerView()
		fetchData()

	}

	private fun fetchData() =
		viewModel.getAccommodations()

	private fun setupRecyclerView() {
		rvAccommodation.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = this@AccommodationsFragment.adapter
		}
	}
}
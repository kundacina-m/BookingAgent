package com.example.bookingagent.screens.accommodations.list

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.utils.FakeData
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_accommodation.fabAddAccommodation
import kotlinx.android.synthetic.main.fragment_accommodation.rvAccommodation

class AccommodationsFragment : BaseFragment<AccommodationsViewModel, AccommodationsRoutes>() {

	private val adapter by lazy {
		AccommodationsAdapter().apply {
			onItemClickListener = this@AccommodationsFragment::itemSelected
		}
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

		setupClickListeners()

		adapter.setData(FakeData.accommodationData)

	}

	private fun setupClickListeners() {
		fabAddAccommodation.setOnClickListener {
			navigation.navigateToAddAccommodation()
		}
	}

	private fun fetchData() =
		viewModel.getAccommodations()

	private fun setupRecyclerView() {
		rvAccommodation.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = this@AccommodationsFragment.adapter
		}
	}

	private fun itemSelected(accommodation: Accommodation) =
		navigation.navigateToSelectedItem(accommodation)

}
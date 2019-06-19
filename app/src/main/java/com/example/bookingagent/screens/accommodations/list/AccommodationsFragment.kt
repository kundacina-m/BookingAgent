package com.example.bookingagent.screens.accommodations.list

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.utils.RequestError.UnknownError
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import com.example.bookingagent.utils.toModel
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
		viewModel.accommodations.observe(this, Observer { response ->
			when (response) {
				is OnSuccess -> {
					arrayListOf<Accommodation>().run {
						response.item.body.body.accommodation?.forEach { add(it.toModel())}
						adapter.setData(this)
					}
				}
				is OnError -> Log.d(TAG, "initView: ${(response.error as UnknownError).t}")
			}
		})
	}

	override fun initView() {

		setupRecyclerView()
		fetchData()

		setupClickListeners()

	}

	private fun setupClickListeners() =
		fabAddAccommodation.setOnClickListener {
			navigation.navigateToAddAccommodation()

		}

	private fun fetchData() =
		viewModel.getAccommodations()

	private fun setupRecyclerView() =
		rvAccommodation.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = this@AccommodationsFragment.adapter
		}

	private fun itemSelected(accommodation: Accommodation) =
		navigation.navigateToSelectedItem(accommodation)

}
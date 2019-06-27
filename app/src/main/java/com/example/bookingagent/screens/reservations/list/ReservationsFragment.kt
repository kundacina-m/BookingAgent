package com.example.bookingagent.screens.reservations.list

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_reservations.rvReservations

class ReservationsFragment : BaseFragment<ReservationsViewModel, ReservationRoutes>() {

	private val adapter by lazy {
		ReservationsAdapter()
	}

	override fun getLayoutId(): Int = R.layout.fragment_reservations

	override fun setObservers() {
		viewModel.reservations.observe(this, Observer {
			when (it) {
				is OnSuccess -> adapter.setData(it.item)
				is OnError -> Log.d(TAG, "setObservers: ERROR")
			}
		})
	}

	override fun initView() {
		setupRecyclerView()
		viewModel.getAllReservations()

	}

	private fun setupRecyclerView() {
		rvReservations.apply {
			layoutManager = LinearLayoutManager(context)
			this.adapter = this@ReservationsFragment.adapter
		}
	}

}
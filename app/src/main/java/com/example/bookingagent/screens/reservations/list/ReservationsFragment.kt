package com.example.bookingagent.screens.reservations.list

import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.fragment_reservations.rvReservations

class ReservationsFragment : BaseFragment<ReservationsViewModel, ReservationRoutes>() {

	private val adapter by lazy {
        ReservationsAdapter()
	}

	override fun getLayoutId(): Int = R.layout.fragment_reservations

	override fun setObservers() {}

	override fun initView() {

		setupRecyclerView()

	}

	private fun setupRecyclerView() {
		rvReservations.apply {
			layoutManager = LinearLayoutManager(context)
			this.adapter = this@ReservationsFragment.adapter
		}
	}

}
package com.example.bookingagent.screens.reservations.list

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_reservations.rvReservations
import kotlinx.android.synthetic.main.toolbar_main.*

class ReservationsFragment : BaseFragment<ReservationsViewModel, ReservationRoutes>() {

	private val adapter by lazy {
		ReservationsAdapter().apply {
			onUsedClicked = this@ReservationsFragment::reservationUsed
		}
	}

	override fun getLayoutId(): Int = R.layout.fragment_reservations

	override fun setObservers() {
		viewModel.reservations.observe(this, Observer {
			when (it) {
				is OnSuccess -> adapter.setData(it.item)
				is OnError -> Log.d(TAG, "setObservers: ERROR")
			}
		})

		viewModel.reservationUsed.observe(this, Observer {
			when (it) {
				is OnSuccess -> Log.d("SUCCES", "SUCCES")
				is OnError -> Log.d(TAG, "setObservers: ERROR")
			}
		})
	}

	override fun initView() {
		setupRecyclerView()
		setActionBar(toolbar_top,false)
		viewModel.getAllReservations()

	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		super.onCreateOptionsMenu(menu, inflater)
	}

	private fun setupRecyclerView() {
		rvReservations.apply {
			layoutManager = LinearLayoutManager(context)
			this.adapter = this@ReservationsFragment.adapter
		}
	}

	private fun reservationUsed(id: Int) {
		viewModel.reservationUsed(id)
	}

}
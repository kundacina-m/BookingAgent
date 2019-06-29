package com.example.bookingagent.screens.home

import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.utils.WrappedResponse
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar_main.*
import kotlinx.android.synthetic.main.view_loading.*

class HomeFragment : BaseFragment<HomeViewModel, HomeRoutes>() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun setObservers() {

        viewModel.accommodationSyncingStatus.observe(this, Observer {
            when (it) {
                is WrappedResponse.OnSuccess -> syncedAccommodationWithBackend()
                is WrappedResponse.OnError -> failedAccommodationSyncing()
            }
        })

        viewModel.reservationSyncingStatus.observe(this, Observer {
            when (it) {
                is WrappedResponse.OnSuccess -> syncedReservationWithBackend()
                is WrappedResponse.OnError -> failedReservationSyncing()
            }
        })

    }


    override fun initView() {
        setActionBar(toolbar_top, false)
        fetchData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun fetchData() {
        viewModel.getAccommodations()
        viewModel.getAllReservations()
        viewModel.getUserInfo()
    }

    override fun onResume() {
        super.onResume()
        accommodationLoading.show()
        reservationLoading.show()
        tvAccommodationSyncing.text = "Accommodation syncing..."
        tvReservationSyncing.text = "Reservation syncing..."
    }

    private fun failedAccommodationSyncing() {
        tvAccommodationSyncing.text = "Accommodation failed in syncing"
        accommodationLoading.hide()
    }

    private fun syncedAccommodationWithBackend() {
        tvAccommodationSyncing.text = "Accommodation successfully synced!"
        accommodationLoading.hide()
    }


    private fun failedReservationSyncing() {
        tvReservationSyncing.text = "Reservations failed in syncing"
        reservationLoading.hide()
    }

    private fun syncedReservationWithBackend() {
        tvReservationSyncing.text = "Reservations successfully synced!"
        reservationLoading.hide()
    }

}
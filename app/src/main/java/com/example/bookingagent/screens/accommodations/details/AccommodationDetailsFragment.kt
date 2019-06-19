package com.example.bookingagent.screens.accommodations.details

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.screens.accommodations.ServicesAdapter
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_accommodation_details.rvServices
import kotlinx.android.synthetic.main.fragment_accommodation_details.tvCancellingFee
import kotlinx.android.synthetic.main.fragment_accommodation_details.tvCity
import kotlinx.android.synthetic.main.fragment_accommodation_details.tvDesc
import kotlinx.android.synthetic.main.fragment_accommodation_details.tvLabelToRooms
import kotlinx.android.synthetic.main.fragment_accommodation_details.tvName
import kotlinx.android.synthetic.main.fragment_accommodation_details.tvRating
import kotlinx.android.synthetic.main.fragment_accommodation_details.tvStreet
import kotlinx.android.synthetic.main.fragment_accommodation_details.tvStreetNum
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class AccommodationDetailsFragment : BaseFragment<AccommodationDetailsViewModel, AccommodationDetailsRoutes>() {

	private val args: AccommodationDetailsFragmentArgs by navArgs()

	private val adapter by lazy {
		ServicesAdapter()
	}

	override fun getLayoutId(): Int = R.layout.fragment_accommodation_details

	override fun setObservers() {

		viewModel.isDeleted.observe(this, Observer {
			when (it) {
				is OnSuccess -> navigation.navigateToAccommodations()
				is OnError -> Log.d(TAG, "setObservers: ERROR")
			}
		})

	}

	override fun initView() {
		actionBarSetup()
		populateViewWithData()

		setupRecyclerView()
		setOnClickListeners()

	}

	private fun setupRecyclerView() {
		rvServices.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = this@AccommodationDetailsFragment.adapter
		}
	}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
		actionBar?.title = args.name
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		inflater.inflate(R.menu.accommodation_menu, menu)
		setOnMenuItemClickListener(menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	private fun setOnMenuItemClickListener(menu: Menu) =
		menu.apply {
			findItem(R.id.editAccommodation).setOnMenuItemClickListener {
				navigation.navigateToEdit(args)
				true
			}
			findItem(R.id.deleteAccommodation).setOnMenuItemClickListener {
				viewModel.deleteAccommodation(args.id)
				true
			}
		}


	private fun setOnClickListeners() =
		tvLabelToRooms.setOnClickListener {
			navigation.navigateToRooms(args.id,args.rooms)
		}

	private fun populateViewWithData() {
		tvName.text = args.name
		tvDesc.text = args.description
		tvCancellingFee.text = args.cancellingFee.toString()
		tvCity.text = args.address.city
		tvStreet.text = args.address.street
		tvStreetNum.text = args.address.num.toString()
		tvRating.text = args.rating.toString()
		adapter.setData(args.services.toList())
	}

}
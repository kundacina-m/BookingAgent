package com.example.bookingagent.screens.accommodations.details

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.AccommodationEntity
import com.example.bookingagent.screens.accommodations.ServicesAdapter
import com.example.bookingagent.screens.rooms.ImagesAdapter
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
import kotlinx.android.synthetic.main.fragment_add_room.rvImages
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class AccommodationDetailsFragment : BaseFragment<AccommodationDetailsViewModel, AccommodationDetailsRoutes>() {

	private val args: AccommodationDetailsFragmentArgs by navArgs()
	private lateinit var accommodationEntity: AccommodationEntity

	private val imagesAdapter by lazy { ImagesAdapter() }
	private val servicesAdapter by lazy { ServicesAdapter() }

	override fun getLayoutId(): Int = R.layout.fragment_accommodation_details

	override fun setObservers() =
		with(viewModel) {

			deletingStatus.observe(this@AccommodationDetailsFragment, Observer {
				when (it) {
					is OnSuccess -> navigation.navigateToAccommodations()
					is OnError -> Log.d(TAG, "setObservers: ERROR")
				}
			})
			accommodation.observe(this@AccommodationDetailsFragment, Observer {
				when (it) {
					is OnSuccess -> onAccommodationDetails(it.item)
					is OnError -> Log.d(TAG, "setObservers: ERROR")
				}
			})
		}

	override fun initView() {
		viewModel.getAccommodationDetails(args.id)

		setupRecyclerView()
		setOnClickListeners()

	}

	private fun onAccommodationDetails(accommodationEntity: AccommodationEntity) {
		this.accommodationEntity = accommodationEntity
		actionBarSetup()
		populateViewWithData()
	}

	private fun setupRecyclerView() {
		rvServices.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = this@AccommodationDetailsFragment.servicesAdapter
		}

		rvImages.apply {
			layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
			adapter = this@AccommodationDetailsFragment.imagesAdapter
		}
	}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
		actionBar?.title = accommodationEntity.name
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
				navigation.navigateToEdit(accommodationEntity)
				true
			}
			findItem(R.id.deleteAccommodation).setOnMenuItemClickListener {
				viewModel.deleteAccommodation(args.id)
				true
			}
		}

	private fun setOnClickListeners() =
		tvLabelToRooms.setOnClickListener {
			navigation.navigateToRooms(args.id)
		}

	private fun populateViewWithData() {
		tvName.text = accommodationEntity.name
		tvDesc.text = accommodationEntity.description
		tvCancellingFee.text = if (accommodationEntity.cancellingFee != 0f)
			accommodationEntity.cancellingFee.toString() else "No cancelling fee"
		tvCity.text = accommodationEntity.address.city
		tvStreet.text = accommodationEntity.address.street
		tvStreetNum.text = accommodationEntity.address.num.toString()
		tvRating.text = if (accommodationEntity.rating != 0f)
			accommodationEntity.rating.toString() else "Not rated yet"
		servicesAdapter.setData(accommodationEntity.services.toList())
		imagesAdapter.setData(accommodationEntity.pictures)
	}

}
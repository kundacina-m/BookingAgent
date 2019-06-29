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
import kotlinx.android.synthetic.main.fragment_accommodation_details.*
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
        setupRecyclerView()
        fetchAccommodationDetails()
        setOnClickListeners()

    }

    private fun fetchAccommodationDetails() =
        viewModel.getAccommodationDetails(args.id)


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

    private fun populateViewWithData() =
        with(accommodationEntity) {
            tvName.text = name
            tvDesc.text = description
            tvCancellingFee.text = if (cancellingFee != 0f) cancellingFee.toString() else "No cancelling fee"
            tvCity.text = address.city
            tvStreet.text = address.street
            tvStreetNum.text = address.num.toString()
            tvRating.text = if (rating != 0f) rating.toString() else "Not rated yet"
            tvCancellingDays.text = if (cancellingDays == 0) "Anytime" else cancellingDays.toString()
            servicesAdapter.setData(services.toList())
            imagesAdapter.setData(pictures)
        }


}
package com.example.bookingagent.screens.accommodations.details

import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.fragment.navArgs
import base.BaseFragment
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.fragment_accommodation_details.*
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class AccommodationDetailsFragment : BaseFragment<AccommodationDetailsViewModel, AccommodationDetailsRoutes>() {


    private val args: AccommodationDetailsFragmentArgs by navArgs()

    override fun getLayoutId(): Int = R.layout.fragment_accommodation_details

    override fun setObservers() {}

    override fun initView() {
        actionBarSetup()
        populateViewWithData()

        tvLabelToRooms.setOnClickListener {
            navigation.navigateToRooms(args.rooms)
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

    private fun setOnMenuItemClickListener(menu: Menu) {

        menu.findItem(R.id.editAccommodation).setOnMenuItemClickListener {
            navigation.navigateToEdit(args)
            true
        }

        menu.findItem(R.id.deleteAccommodation).setOnMenuItemClickListener {
            true
        }
    }

    private fun populateViewWithData() {
        tvName.text = args.name
        tvDesc.text = args.description
        tvCancellingFee.text = args.cancellingFee.toString()
        tvCity.text = args.address.city
        tvStreet.text = args.address.street
        tvStreetNum.text = args.address.num.toString()
        tvRating.text = args.rating.toString()

    }

}
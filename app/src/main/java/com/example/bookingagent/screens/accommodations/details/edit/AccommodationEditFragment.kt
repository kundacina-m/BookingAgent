package com.example.bookingagent.screens.accommodations.details.edit

import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.fragment.navArgs
import base.BaseFragment
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.fragment_accommodation_edit.etAddress
import kotlinx.android.synthetic.main.fragment_accommodation_edit.etCity
import kotlinx.android.synthetic.main.fragment_accommodation_edit.etDescription
import kotlinx.android.synthetic.main.fragment_accommodation_edit.etLatitude
import kotlinx.android.synthetic.main.fragment_accommodation_edit.etLongitude
import kotlinx.android.synthetic.main.fragment_accommodation_edit.etName
import kotlinx.android.synthetic.main.fragment_accommodation_edit.etNum
import kotlinx.android.synthetic.main.fragment_accommodation_edit.etZipCode
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class AccommodationEditFragment : BaseFragment<AccommodationEditViewModel, AccommodationEditRoutes>() {

	private val args: AccommodationEditFragmentArgs by navArgs()

	override fun getLayoutId(): Int = R.layout.fragment_accommodation_edit

	override fun setObservers() {}

	override fun initView() {
		actionBarSetup()
		fillViewWithData()
	}

	private fun fillViewWithData() {
		etName.setText(args.name)
		etDescription.setText(args.description)
		etLatitude.setText(args.address.latitude.toString())
		etLongitude.setText(args.address.longitude.toString())
		etCity.setText(args.address.city)
		etZipCode.setText(args.address.zipCode.toString())
		etAddress.setText(args.address.street)
		etNum.setText(args.address.num.toString())

	}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
		actionBar?.title = args.name
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		super.onCreateOptionsMenu(menu, inflater)
	}

}